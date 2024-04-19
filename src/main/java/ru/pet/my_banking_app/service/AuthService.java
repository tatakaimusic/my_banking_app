package ru.pet.my_banking_app.service;

import ru.pet.my_banking_app.web.dto.auth.JwtRequest;
import ru.pet.my_banking_app.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}
