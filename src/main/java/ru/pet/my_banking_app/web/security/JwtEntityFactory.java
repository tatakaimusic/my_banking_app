package ru.pet.my_banking_app.web.security;


import ru.pet.my_banking_app.domen.User;

public class JwtEntityFactory {

    public static JwtEntity create(User user) {
        return new JwtEntity(user.getId(), user.getEmail(), user.getPassword());
    }

}
