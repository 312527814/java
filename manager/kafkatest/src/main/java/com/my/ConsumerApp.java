package com.my;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;


import java.time.Duration;
import java.util.*;

public class ConsumerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.77.130:9092");
        props.setProperty("group.id", "testt1");
//        props.setProperty("enable.auto.commit", "true");
//        props.setProperty("auto.commit.interval.ms", "100");
        props.put("max.poll.records", "2");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("my-topic"));
//        List<PartitionInfo> partitionInfos = consumer.partitionsFor("my-topic");
//        if (partitionInfos != null) {
//            partitionInfos.forEach(p -> {
//                if (p.partition() == 1) {
//                    Collection<TopicPartition> list = new ArrayList<>();
//                    TopicPartition topicPartition = new TopicPartition(p.topic(), p.partition());
//                    list.add(topicPartition);
//                    consumer.assign(list);
//                }
//            });
//        }

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000 * 4);
            //System.out.println("records size " + records.count());
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }
}
