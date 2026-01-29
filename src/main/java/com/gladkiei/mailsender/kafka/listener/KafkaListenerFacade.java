package com.gladkiei.mailsender.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.nio.charset.StandardCharsets;

public class KafkaListenerFacade {

    ListenerConfig listenerConfig = new ListenerConfig();

    @KafkaListener(
            topics = "EMAIL_SENDING_TASKS",
            groupId = "email-sender-group"
    )
    public void listen(@Payload Class<?> payload,
                       @Header("event-type") byte[] eventTypeBytes) {
        String eventType = new String(eventTypeBytes, StandardCharsets.UTF_8);
        EventListener<?> eventListener = listenerConfig.listeners.get(eventType);

        eventListener.listen(payload);
    }
}
