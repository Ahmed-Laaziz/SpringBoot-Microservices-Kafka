package spark;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataProcessing {
    public static void main(String[] args) throws InterruptedException {
        // Create a Spark configuration
        SparkConf sparkConf = new SparkConf()
                .setAppName("SparkKafkaIntegration")
                .setMaster("local[*]"); // Run Spark locally using all available cores

        // Create a JavaStreamingContext with a batch interval of 1 second
        JavaStreamingContext streamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(1));

        // Set Kafka broker(s) and topic(s) to consume from
        String brokers = "localhost:9092"; // Replace with your Kafka broker(s) configuration
        String topics = "bdccTopic"; // Replace with your Kafka topic(s)

        // Set Kafka consumer configuration
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", brokers);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "spark-consumer-group"); // Set your consumer group
        kafkaParams.put("auto.offset.reset", "latest");

        // Create a JavaInputDStream from Kafka using the provided configuration
        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        streamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(Collections.singleton(topics), kafkaParams)
                );

        // Process each RDD from the stream
        stream.foreachRDD(rdd -> {
            // Check if the RDD contains any data
            if (!rdd.isEmpty()) {
                // Collect the messages and print them
                rdd.map(ConsumerRecord::value).foreach(System.out::println);
            }
        });

        // Start the streaming context
        streamingContext.start();

        // Wait for the termination signal
        streamingContext.awaitTermination();
    }
}
