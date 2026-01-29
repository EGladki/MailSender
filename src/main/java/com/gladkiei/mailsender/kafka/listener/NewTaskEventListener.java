package com.gladkiei.mailsender.kafka.listener;

import com.gladkiei.mailsender.dtos.TaskResponseDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

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
    public void listen(@Payload TaskResponseDto taskResponseDto, Acknowledgment acknowledgment) {
        System.out.printf("Received new task created: %s%n", taskResponseDto);
        acknowledgment.acknowledge();
    }

}
