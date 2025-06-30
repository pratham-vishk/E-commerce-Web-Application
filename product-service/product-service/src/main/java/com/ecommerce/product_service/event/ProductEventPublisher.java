package com.ecommerce.product_service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String PRODUCT_CREATED_TOPIC = "product.created";
    private static final String PRODUCT_UPDATED_TOPIC = "product.updated";
    private static final String PRODUCT_DELETED_TOPIC = "product.deleted";

    public void publishProductCreated(Object event) {
        sendEvent(PRODUCT_CREATED_TOPIC, event);
    }

    public void publishProductUpdated(Object event) {
        sendEvent(PRODUCT_UPDATED_TOPIC, event);
    }

    public void publishProductDeleted(Object event) {
        sendEvent(PRODUCT_DELETED_TOPIC, event);
    }

    private void sendEvent(String topic, Object event) {
        try {
            kafkaTemplate.send(topic, event);
            log.info("Event sent to topic [{}]: {}", topic, event);
        } catch (Exception e) {
            log.error("Failed to send event to topic [{}]: {}", topic, e.getMessage(), e);
        }
    }
}
