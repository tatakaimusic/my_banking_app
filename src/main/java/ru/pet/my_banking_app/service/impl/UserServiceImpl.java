package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pet.my_banking_app.domen.Card;
import ru.pet.my_banking_app.domen.User;
import ru.pet.my_banking_app.domen.exception.ResourceNotFoundException;
import ru.pet.my_banking_app.repository.CardRepository;
import ru.pet.my_banking_app.repository.UserRepository;
import ru.pet.my_banking_app.service.UserService;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CardRepository cardRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cardRepository = cardRepository;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User with this email doesn't exist!"
                        )
                );
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User with this id doesn't exist!"
                        )
                );
    }

    @Override
    public User create(User user) {
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException(
                    "User with this email already exist!"
            );
        }

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );
        User createdUser = userRepository.save(user);
        Card card = new Card(generateNumber(), BigDecimal.ZERO, createdUser);
        cardRepository.save(card);
        return createdUser;
    }

    private Long generateNumber() {
        Random rnd = new Random();
        char[] digits = new char[10];
        digits[0] = (char) (rnd.nextInt(9) + '1');
        for (int i = 1; i < digits.length; i++) {
            digits[i] = (char) (rnd.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

}
