package com.example.backendproject;

import com.example.backendproject.models.Queue;
import com.example.backendproject.repo.QueueRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

//@Component
//@ComponentScan
//public class ReadQueueApp implements CommandLineRunner {

   /* @Autowired
    QueueRepo queueRepo;
    String queueName = "8ed3f77d-5fd0-498a-bb4f-d788a779bb2a";
    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("128.140.81.47");
        factory.setUsername("djk47589hjkew789489hjf894");
        factory.setPassword("sfdjkl54278frhj7");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");

            try {
                Queue newQueue = mapper.readValue(message, Queue.class);
                queueRepo.save(newQueue);
                System.out.println("Queue: " + newQueue.id + " is saved");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // https://www.baeldung.com/jackson-annotations#bd-jackson-polymorphic-type-handling-annotations
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }*/
//}
