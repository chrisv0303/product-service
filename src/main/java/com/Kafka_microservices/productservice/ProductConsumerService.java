package com.Kafka_microservices.productservice;

import org.exporting.dependency.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumerService {

    @KafkaListener(topics = "order-events")
    public void handleOrderEvent(OrderEvent orderEvent) {
        if("ORDER_PLACED".equals(orderEvent.getEventType())) {
            System.out.println("Received ORDER_PLACED event: Order ID - " + orderEvent.getOrderId());
        }
    }
}
