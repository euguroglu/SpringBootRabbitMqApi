package com.enesuguroglu.SpringBootRabbitMqApi.Consumer;

import com.enesuguroglu.SpringBootRabbitMqApi.Model.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class User {

    @RabbitListener(queues = "${rabbit.queue.name2}")
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from queue : "+orderStatus);
    }
}
