package com.gladkiei.mailsender.api.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class UnisenderApiClient implements EmailServiceSenderApiClient {

    private final String baseUrl = "https://api.unisender.com/ru/api/";
    private final String sendEmailUrl = "sendEmail";
    private final String subscribeUrl = "subscribe";
    private final String apiKey = "6y3teybgpt7ofnm6a3dhotmieiwsfqu5js11zuoe";
    private final String senderName = "Task tracker";
    private final String subject = "Task tracker message";
    private final String senderEmail = "e.gladkiy@eapteka.ru";
    private final String body = "Thanks for registration";
    private final String listId = "2";
    private final String htmlBody = """
            <body>
            <h1>Hello from Task tracker</h1>
            <p>Thanks for registration</p>
            </body>
            """;

    public void sendEmail(String email) {
        WebClient client = WebClient.create(baseUrl);
        String response = client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(sendEmailUrl)
                        .queryParam("format", "json")
                        .queryParam("api_key", apiKey)
                        .queryParam("email", email)
                        .queryParam("sender_email", senderEmail)
                        .queryParam("sender_name", senderName)
                        .queryParam("subject", subject)
                        .queryParam("list_id", listId)
                        .queryParam("body", htmlBody)
                        .queryParam("error_checking", 1)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(res -> log.info("Unisender response: {}", res))
                .block();

        log.info(response);
    }

    @Override
    public void subscribe(String email) {
        WebClient client = WebClient.create(baseUrl);

        LinkedMultiValueMap<String, String> defaultUriVariables = new LinkedMultiValueMap<>();
        defaultUriVariables.add("format", "json");
        defaultUriVariables.add("api_key", apiKey);
        defaultUriVariables.add("list_ids", "2");
        defaultUriVariables.add("fields[email]", email);
        defaultUriVariables.add("double_optin", "3");
//        defaultUriVariables.add("fields[email]", "adolphippler@atomicmail.io");

        String response = client.get().
                uri(uriBuilder -> uriBuilder.
                        path(subscribeUrl)
                        .queryParam("format", "json")
                        .queryParam("api_key", apiKey)
                        .queryParam("list_ids", "2")
                        .queryParam("fields[email]", email)
                        .queryParam("double_optin", "3")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(res -> log.info("Unisender response: {}", res))
                .block();

        log.info(response);
    }

}
