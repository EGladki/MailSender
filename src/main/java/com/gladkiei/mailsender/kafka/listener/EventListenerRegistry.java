package com.gladkiei.mailsender.kafka.listener;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventListenerRegistry {

    private final Map<String, EventListener<?>> eventListeners;

    public EventListenerRegistry(List<EventListener<?>> eventListenersList) {
        this.eventListeners = eventListenersList.stream().
                collect(Collectors.toMap(EventListener::eventType, Function.identity()));
    }

    public EventListener<?> get(String eventType) {
        return eventListeners.get(eventType);
    }
}
