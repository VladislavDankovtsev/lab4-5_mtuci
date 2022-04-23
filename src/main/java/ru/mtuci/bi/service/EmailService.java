package ru.mtuci.bi.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
