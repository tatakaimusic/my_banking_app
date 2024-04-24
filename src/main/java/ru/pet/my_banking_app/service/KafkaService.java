package ru.pet.my_banking_app.service;

public interface KafkaService {

    void sendEmailConfirmation(String email);

}
