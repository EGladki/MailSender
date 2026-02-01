package com.gladkiei.mailsender.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerFacade {

    private final ObjectMapper objectMapper;
    private final EventListenerRegistry registry;
    private final EventListenerInvoker eventListenerInvoker;

    @KafkaListener(
            topics = "EMAIL_SENDING_TASKS",
            groupId = "email-sender-group"
    )
    public void listen(@Payload String json,
                       @Header("event-type") String eventType,
                       Acknowledgment acknowledgment) throws JsonProcessingException {
        EventListener<?> eventListener = registry.get(eventType);
        Object o = objectMapper.readValue(json, eventListener.payloadType());
        log.info("Deserialized json object: {}", o);

        eventListenerInvoker.invoke(eventListener, o);

        acknowledgment.acknowledge();
    }
}
