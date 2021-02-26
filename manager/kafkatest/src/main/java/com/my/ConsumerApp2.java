package com.my;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class ConsumerApp2 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.77.130:9092");
        props.setProperty("group.id", "testt1");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "100");
        props.put("max.poll.records", "2");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//        consumer.subscribe(Arrays.asList("test8"));
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("my-topic");
        Collection<TopicPartition> list = new ArrayList<>();
        if (partitionInfos != null) {
            partitionInfos.forEach(p -> {
                TopicPartition topicPartition = new TopicPartition(p.topic(), p.partition());
                list.add(topicPartition);
            });
        }
        consumer.assign(list);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000 * 4);
            //System.out.println("records size " + records.count());
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
        }


    }
}
