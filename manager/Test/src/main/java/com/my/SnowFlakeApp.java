package com.my;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-27 18:43
 */
public class SnowFlakeApp {

    static LinkedBlockingQueue<Long> list = new LinkedBlockingQueue<Long>();
    static List<Thread> l = new ArrayList();

    public static void main(String[] args) {
        SnowFlakeApp worker = new SnowFlakeApp(1, 1, 1);
        for (int i = 0; i < 30; i++) {
            long l = worker.nextId();
            System.out.println(l);
        }

    }

    public static void main2(String[] args) {
        for (int i = 0; i < 30; i++) {

            Thread thread = new Thread(() -> {
                Random random = new Random();
                int i1 = random.nextInt(30);
                System.out.println("i" + i1);
                SnowFlakeApp worker = new SnowFlakeApp(i1, 1, 1);
                long l = worker.nextId();
                list.add(l);
            });
            l.add(thread);
        }


        for (int i = 0; i < 30; i++) {
            l.get(i).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            Long poll = null;
            try {
                poll = list.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(poll);
        }

    }


    private long workerId;
    private long datacenterId;
    private long sequence;

    public SnowFlakeApp(long workerId, long datacenterId, long sequence) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
//            System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
//                    timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    private long twepoch = 0;// 1288834974657L;

    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return 2199023255551l;
//        return System.currentTimeMillis();
    }
}
