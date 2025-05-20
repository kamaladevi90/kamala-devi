package com.kamala.example.kafka.multipartitiondemo;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {
        String topicName = "test-topic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 1; i <= 10; i++) {
            String key = "key" + (i % 3); // key0, key1, key2 - will route to same partitions
            String value = "Message " + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.printf("Sent to partition %d: %s%n", metadata.partition(), value);
                } else {
                    exception.printStackTrace();
                }
            });
        }

        producer.close();
    }
}
