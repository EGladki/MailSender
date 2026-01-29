package com.gladkiei.mailsender.listener;

import com.gladkiei.mailsender.dtos.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class KafkaEventListener {
    @KafkaListener(
            id = "${kafka.listener.id}",
            topics = "${kafka.topic.email-sending-tasks}",
            groupId = "${kafka.group.id}"
    )

    public void listen(
            @Payload UserResponseDto userResponseDto,
            @Header("event-type") byte[] eventTypeBytes,
            Acknowledgment acknowledgment) {
        String eventType = new String(eventTypeBytes, StandardCharsets.UTF_8);

        if (eventType.equals("${kafka.event.type.welcome}")) {
            System.out.printf("Received event-type: %s%n", eventType);
            System.out.printf("Received email sending tasks: %s%n", userResponseDto);
            acknowledgment.acknowledge();
        }

    }

}
