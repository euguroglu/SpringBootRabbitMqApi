package com.enesuguroglu.SpringBootRabbitMqApi.Controller;

import com.enesuguroglu.SpringBootRabbitMqApi.Model.Order;
import com.enesuguroglu.SpringBootRabbitMqApi.Model.OrderStatus;
import com.enesuguroglu.SpringBootRabbitMqApi.Producer.OrderProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class RabbitMqController {

    @Autowired
    OrderProducer orderproducer;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderstatus = new OrderStatus(order, "PROCESS", "order placed succesfully in "+restaurantName);
        orderproducer.sendToQueue(orderstatus);
        return "Success";
    }
}
