package com.my.CreateThread;

import com.sun.jmx.snmp.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TT2 {
    public static void main(String[] args) throws IOException {
        List<Future<String>> list = new ArrayList<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 10; i++) {
            int corePoolSize = executor.getCorePoolSize();
            long taskCount = executor.getTaskCount();
            int poolSize = executor.getPoolSize();
            int largestPoolSize = executor.getLargestPoolSize();
            int activeCount = executor.getActiveCount();
            int size = executor.getQueue().size();
            BlockingQueue<Runnable> queue = executor.getQueue();
            System.out.println("corePoolSize:{" + corePoolSize + "}");
            System.out.println("taskCount:{" + taskCount + "}");
            System.out.println("poolSize:{" + poolSize + "}");
            System.out.println("largestPoolSize:{" + largestPoolSize + "}");
            System.out.println("activeCount:{" + activeCount + "}");
            System.out.println("executor.getQueue().size():{" + size + "}");

            System.out.println("task" + executor.getQueue() + "}");
            System.out.println("..........................................");

            Future<String> submit = executor.submit(new MyTask(i));
            list.add(submit);
        }

//
//        System.in.read();


    }

    public static class MyTask implements Callable<String> {
        private int _i;

        public MyTask(int i) {
            this._i = i;
        }

        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            System.in.read();
            if (_i == 2) {
                throw new Exception("一定会受到");
            }
            return _i + "";
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + _i +
                    '}';
        }
    }
}
