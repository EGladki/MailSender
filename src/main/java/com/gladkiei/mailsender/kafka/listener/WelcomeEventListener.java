package com.gladkiei.mailsender.kafka.listener;

import com.gladkiei.mailsender.api.client.EmailServiceSenderApiClient;
import com.gladkiei.mailsender.dtos.RegistrationEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

@Slf4j
public class WelcomeEventListener implements EventListener<RegistrationEventDto> {

    private final EmailServiceSenderApiClient emailServiceSenderApiClient;

    @Override
    public String eventType() {
        return "WELCOME";
    }

    @Override
    public Class<RegistrationEventDto> payloadType() {
        return RegistrationEventDto.class;
    }

    @Override
    public void listen(@Payload RegistrationEventDto registrationEventDto) {
        System.out.printf("Received email sending tasks: %s%n", registrationEventDto);

//        log.info("Subscribing");
//        emailServiceSenderApiClient.subscribe(userResponseDto.email());

        log.info("Sending email");
        emailServiceSenderApiClient.sendEmail(registrationEventDto.email());
    }
}
