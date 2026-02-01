package com.gladkiei.mailsender.api.client;

public interface EmailServiceSenderApiClient {

    void sendEmail(String email);

    void subscribe(String email);
}
