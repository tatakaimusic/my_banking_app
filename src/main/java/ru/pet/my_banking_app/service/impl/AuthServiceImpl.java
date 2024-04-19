package ru.pet.my_banking_app.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.pet.my_banking_app.domen.User;
import ru.pet.my_banking_app.service.AuthService;
import ru.pet.my_banking_app.service.UserService;
import ru.pet.my_banking_app.web.dto.auth.JwtRequest;
import ru.pet.my_banking_app.web.dto.auth.JwtResponse;
import ru.pet.my_banking_app.web.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = userService.getByEmail(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getEmail());
        jwtResponse.setAccessToken(
                jwtTokenProvider.createAccessToken(
                        user.getId(), user.getEmail()
                )
        );
        jwtResponse.setRefreshToken(
                jwtTokenProvider.createRefreshToken(
                        user.getId(), user.getEmail()
                )
        );
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }

}
