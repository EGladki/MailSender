package com.gladkiei.mailsender.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EventListener<T> {

    String eventType();

    Class<T> payloadType();

    void listen(T t) throws JsonProcessingException;

}
