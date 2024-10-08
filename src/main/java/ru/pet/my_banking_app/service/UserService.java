package ru.pet.my_banking_app.service;

import ru.pet.my_banking_app.domen.User;

public interface UserService {

    User getByEmail(String email);

    User getById(Long id);

    User register(User user, String code);

    void sendConfirmationEmail(String email);

    void sendResetEmail(String email);

}
