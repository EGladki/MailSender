package com.gladkiei.mailsender.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladkiei.mailsender.dtos.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewTaskEventListener implements EventListener<TaskResponseDto> {

    @Override
    public String eventType() {
        return "NEW_TASK";
    }

    @Override
    public Class<TaskResponseDto> payloadType() {
        return TaskResponseDto.class;
    }

    @Override
    public void listen(@Payload TaskResponseDto taskResponseDto) throws JsonProcessingException {
        System.out.printf("Received new task created: %s%n", taskResponseDto);
    }

}
