package com.gladkiei.mailsender.listener;

import com.gladkiei.mailsender.dtos.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class KafkaWelcomeEventListener implements KafkaEventHandler<UserResponseDto> {
    @Override
    public String eventType() {
        return "${kafka.event.type.welcome}";
    }

    @Override
    public Class<UserResponseDto> payload() {
        return UserResponseDto.class;
    }

    @Override
    public void listen(UserResponseDto payload) {
        System.out.printf("Send welcome email to " + payload.email());
    }


}

