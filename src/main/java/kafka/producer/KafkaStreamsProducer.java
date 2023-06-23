package kafka.producer;

import ma.ensaj.school_microservice.Models.Student;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class KafkaStreamsProducer {

    public void sendStudentToProducer(Student student) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "streams-prod-1");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // Calculate age from birthday
        LocalDate birthday = student.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthday, currentDate).getYears();

        // Convert Student object to byte array
        String studentData = student.getFirst_name() + "|" + student.getLast_name() + "|" + student.getEmail() + "|"
                + student.getGender() + "|" + student.getPhone() + "|" + student.getAddress() + "|" + student.getCin()
                + "|" + student.getCne() + "|" + age + "|" + student.getSchool_name();

        kafkaProducer.send(new ProducerRecord<>("bdccTopic", null, studentData), (md, ex) -> {
            if (ex == null) {
                System.out.println("Sending Student data to topic " + md.topic() +
                        " Partition: " + md.partition());
            } else {
                System.err.println("Error sending Student data: " + ex.getMessage());
            }
        });
    }
}



/*
package kafka.producer;

import ma.ensaj.school_microservice.Models.Student;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import java.time.LocalDate;

public class KafkaStreamsProducer {

    public void sendStudentToProducer(Student student) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "streams-prod-1");

        KafkaProducer<String, byte[]> kafkaProducer = new KafkaProducer<>(properties);
        //current date
        LocalDate currentDate = LocalDate.now();
        // Convert Student object to byte array
        byte[] studentData;
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
            objectStream.writeObject(student);
            objectStream.flush();
            studentData = byteStream.toByteArray();
        } catch (IOException e) {
            System.err.println("Error serializing Student object: " + e.getMessage());
            return;
        }

        kafkaProducer.send(new ProducerRecord<>("bdccTopic", null, studentData), (md, ex) -> {
            if (ex == null) {
                System.out.println("Sending Student data to topic " + md.topic() +
                        " Partition: " + md.partition());
            } else {
                System.err.println("Error sending Student data: " + ex.getMessage());
            }
        });

        kafkaProducer.close();
    }
}

*/
