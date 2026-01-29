package com.gladkiei.mailsender.kafka.listener;

import java.util.Map;

public class ListenerConfig {

    public Map<String, EventListener<?>> listeners;

    public Map<String, EventListener<?>> listeners(WelcomeEventListener welcomeEventListener, NewTaskEventListener newTaskEventListener) {
        return Map.of("WELCOME", welcomeEventListener,
                "NEW_TASK", newTaskEventListener);
    }
}
