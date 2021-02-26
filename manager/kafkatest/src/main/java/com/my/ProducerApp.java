package com.my;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.consumer.internals.PartitionAssignor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerApp {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.77.130:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 1638400);
        props.put("linger.ms", 1000 * 10);
//        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; ; i++) {
//            if (i == 3) {
//                i = 10 / 0;
//            }
            Scanner sc = new Scanner(System.in);

            String s = sc.nextLine();//读取字符串
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), s + ":" + Integer.toString(i)));
        }


//        Thread.sleep(1000 * 2);
//        System.in.read();
//        producer.close();
    }
}
