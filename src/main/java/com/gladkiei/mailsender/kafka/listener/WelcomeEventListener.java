package com.gladkiei.mailsender.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladkiei.mailsender.dtos.TaskResponseDto;
import com.gladkiei.mailsender.dtos.UserResponseDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class WelcomeEventListener implements EventListener<UserResponseDto> {

    @Override
    public String eventType() {
        return "WELCOME";
    }

    @Override
    public Class<UserResponseDto> payloadType() {
        return UserResponseDto.class;
    }

    @Override
    public void listen(@Payload UserResponseDto userResponseDto) throws JsonProcessingException {
        System.out.printf("Received email sending tasks: %s%n", userResponseDto);
    }
}
