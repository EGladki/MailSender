package com.gladkiei.mailsender.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public class EventListenerInvoker {

    @SuppressWarnings("unchecked")
    public <T> void invoke(EventListener<T> eventListener, Object payload) throws JsonProcessingException {
        eventListener.listen((T) payload);
    }
}
