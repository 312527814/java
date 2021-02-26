package com.my.transaction;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerTranApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.77.130:9092");
        props.setProperty("group.id", "test3");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "100000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.ISOLATION_LEVEL_CONFIG,"read_committed");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test7", "my-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000 * 4 * 100);
            System.out.println("............................records size " + records.count());
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
        }
    }
}
