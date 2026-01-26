package com.gladkiei.mailsender;

import com.gladkiei.mailsender.dtos.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Listener {

//    @KafkaListener(
//            topics = "EMAIL_SENDING_TASKS",
//            groupId = "email-sender-group"
//    )
//    public void listen(
//            @Payload String payload,
//            @Header("event-type") byte[]  eventTypeBytes
//    ) {
//        String eventType = new String(eventTypeBytes, StandardCharsets.UTF_8);
//        System.out.println("eventType: " + eventType);
////        System.out.println("=== MESSAGE FROM KAFKA ===");
////        System.out.println("id: " + userResponseDto.id());
////        System.out.println("email: " + userResponseDto.email());
//    }

    @KafkaListener(
            topics = "EMAIL_SENDING_TASKS",
            groupId = "email-sender-group"
    )
    public void listen(UserResponseDto userResponseDto) {
        System.out.printf("Received email sending tasks: %s%n", userResponseDto);
    }

}
