package com.enesuguroglu.SpringBootRabbitMqApi.Controller;

import com.enesuguroglu.SpringBootRabbitMqApi.Model.Order;
import com.enesuguroglu.SpringBootRabbitMqApi.Model.OrderStatus;
import com.enesuguroglu.SpringBootRabbitMqApi.Producer.OrderProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class RabbitMqController {

    final Logger logger = LogManager.getLogger(RabbitMqController.class.getName());

    @Autowired
    OrderProducer orderproducer;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) throws Exception {
        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderstatus = new OrderStatus(order, "PROCESS", "order placed succesfully in "+restaurantName);

        try {
            orderproducer.sendToQueue(orderstatus);
        } catch (AmqpException e) {
            logger.fatal("Unable to create connection", e);
        }

        return "Success";
    }
}
