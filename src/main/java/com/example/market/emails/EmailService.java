package com.example.market.emails;


public interface EmailService {

    void send(String to, String email, byte[] bytes);

    String createEmail(String name, String lastName);

    void sendHelpMessage(String to, String email);
}