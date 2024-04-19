package ru.pet.my_banking_app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pet.my_banking_app.domen.User;
import ru.pet.my_banking_app.service.AuthService;
import ru.pet.my_banking_app.service.UserService;
import ru.pet.my_banking_app.web.dto.UserDTO;
import ru.pet.my_banking_app.web.dto.auth.JwtRequest;
import ru.pet.my_banking_app.web.dto.auth.JwtResponse;
import ru.pet.my_banking_app.web.mapper.UserMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, UserMapper userMapper, AuthService authService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserDTO register(
            @RequestBody UserDTO dto
    ) {
        User user = userMapper.toEntity(dto);
        return userMapper.toDto(
                userService.create(user)
        );
    }

    @PostMapping("/login")
    public JwtResponse login(
            @RequestBody JwtRequest loginRequest
    ) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(
            @RequestParam(value = "token") String token
    ) {
        return authService.refresh(token);
    }

}
