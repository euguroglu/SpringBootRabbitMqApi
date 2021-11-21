package com.enesuguroglu.SpringBootRabbitMqApi.Producer;

import com.enesuguroglu.SpringBootRabbitMqApi.Model.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Value("${rabbit.binding.name}")
    private String bindingName;

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate template;

    public void sendToQueue(OrderStatus orderstatus){
        template.convertAndSend(exchangeName,bindingName,orderstatus);
    }
}
