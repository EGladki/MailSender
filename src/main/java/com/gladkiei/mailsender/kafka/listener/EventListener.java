package com.gladkiei.mailsender.kafka.listener;

import org.springframework.kafka.support.Acknowledgment;

public interface EventListener<T> {

    String eventType();

    Class<T> payloadType();

    void listen(T t, Acknowledgment acknowledgment);

}
