package ru.pet.my_banking_app.config;

public class RedisSchema {

    public static String emailKeys(String email){
        return KeyHelper.getKey(email);
    }

}
