package ru.pet.my_banking_app.repository;

public interface EmailRedisRepository {

    String getCode(String email);

    void deleteCode(String email);

}
