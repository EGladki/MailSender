package com.gladkiei.mailsender.listener;

public interface KafkaEventHandler<T> {

    String eventType();

    Class<T> payload();

    void listen(T payload);
}
