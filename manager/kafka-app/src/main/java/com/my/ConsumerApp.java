package com.my;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Deserializer;
import org.junit.Test;

import java.util.*;

public class ConsumerApp {

    @Test
    public void test() {
        int pa = Math.abs("group-test4".hashCode()) % 50;
        System.out.println(pa);
    }

    @Test
    public void consumer() {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.77.135:9092");
//        props.setProperty("bootstrap.servers", "192.168.77.135:9092");
        props.setProperty("group.id", "group-test4");
        props.setProperty("enable.auto.commit", "false");
//        props.setProperty("auto.commit.interval.ms", "100");
        props.put("max.poll.records", "2");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test5"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000 * 40);
            //System.out.println("records size " + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());

                OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(record.offset() + 1);
                TopicPartition topicPartition = new TopicPartition(record.topic(), record.partition());
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                offsets.put(topicPartition, offsetAndMetadata);

                consumer.commitAsync(offsets, new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        if (exception != null) {
                            System.out.println(exception.toString());
                        }
                    }
                });
            }
        }
    }

    @Test
    public void consumerPartition() {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.77.135:9092");
        props.setProperty("group.id", "testGroup");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "100");
        props.put("max.poll.records", "2");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

//        consumer.subscribe(Arrays.asList("test3"));
        Collection<TopicPartition> list = new ArrayList<>();
        TopicPartition partition0 = new TopicPartition("test3", 0);
        list.add(partition0);
        consumer.assign(list);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(4);
            //System.out.println("records size " + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
//                OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(record.offset() + 1);
//                TopicPartition topicPartition = new TopicPartition(record.topic(), record.partition());
//                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
//                offsets.put(topicPartition, offsetAndMetadata);
//                consumer(offsets,null);
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        System.out.println("callback " + offsets);
                    }
                });
            }
        }
    }

    @Test
    public void consumerPartition1() {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.77.135:9092");
        props.setProperty("group.id", "testGroup");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "100");
        props.put("max.poll.records", "2");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

//        consumer.subscribe(Arrays.asList("test3"));
        Collection<TopicPartition> list = new ArrayList<>();
        TopicPartition partition0 = new TopicPartition("test3", 1);
        list.add(partition0);
        consumer.assign(list);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(4);
            //System.out.println("records size " + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
                OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(record.offset());
                TopicPartition topicPartition = new TopicPartition(record.topic(), record.partition());
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                offsets.put(topicPartition, offsetAndMetadata);
                consumer.commitAsync(offsets, new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        System.out.println("oncomplete........");
                    }
                });
//                consumer.commitAsync();
            }
        }
    }
}
