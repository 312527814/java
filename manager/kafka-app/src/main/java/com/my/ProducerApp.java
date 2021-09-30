package com.my;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerApp {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.77.135:9092");
        props.put("acks", "-1");
//        props.put("retries", 0);
        props.put("batch.size", 1638400);
        props.put("linger.ms", 1000 * 1);
//        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
//        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 20; i++) {
//            if (i == 3) {
//                i = 10 / 0;
//            }
//            Scanner sc = new Scanner(System.in);
//
//            String s = sc.nextLine();//读取字符串

            String s = "xxooooxxooxxooxx的";
            Future<RecordMetadata> test1 = producer.send(new ProducerRecord<>("test3", s + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    long offset = metadata.offset();
                    int partition = metadata.partition();
                    String topic = metadata.topic();

                    System.out.println("topic:= " + topic + " partition:= " + partition + " offset:= " + offset);
                }
            });
//            RecordMetadata recordMetadata = test1.get();

//            Thread.sleep(300);
        }


//        Thread.sleep(1000 * 2);
        System.in.read();
//        producer.close();
    }

    @Test
    public void ConsumerOffsets() throws Exception {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.77.135:9092");
        props.put("acks", "-1");
//        props.put("retries", 0);
        props.put("batch.size", 1638400);
        props.put("linger.ms", 1000 * 1);
//        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
//        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 20; i++) {
//            if (i == 3) {
//                i = 10 / 0;
//            }
//            Scanner sc = new Scanner(System.in);
//
//            String s = sc.nextLine();//读取字符串

            String s = "xxooooxxooxxooxx的";
            Future<RecordMetadata> test1 = producer.send(new ProducerRecord<>("__consumer_offsets", 49, "", s + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    long offset = metadata.offset();
                    int partition = metadata.partition();
                    String topic = metadata.topic();

                    System.out.println("topic:= " + topic + " partition:= " + partition + " offset:= " + offset);
                }
            });
//            RecordMetadata recordMetadata = test1.get();

//            Thread.sleep(300);
        }


//        Thread.sleep(1000 * 2);
        System.in.read();
//        producer.close();
    }

    /*
     * 发送到指定的partition
     * */
    @Test
    public void producerPartition() {

        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.77.135:9092");
        props.put("acks", "-1");
        props.put("batch.size", 1638400);
        props.put("linger.ms", 1000 * 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 20; i++) {
            String s = "xxooooxxooxxooxx的";
            ProducerRecord<String, String> record = new ProducerRecord<>("test4", 1, "key", s + i);
            Integer partition = record.partition();
            Future<RecordMetadata> test1 = producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    long offset = metadata.offset();
                    int partition = metadata.partition();
                    String topic = metadata.topic();

                    System.out.println("topic:= " + topic + " partition:= " + partition + " offset:= " + offset);
                }
            });
            try {
                RecordMetadata recordMetadata = test1.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

//            Thread.sleep(300);


        }

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
