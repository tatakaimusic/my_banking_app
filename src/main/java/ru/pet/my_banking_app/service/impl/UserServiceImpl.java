package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.my_banking_app.domen.User;
import ru.pet.my_banking_app.domen.exception.ResourceNotFoundException;
import ru.pet.my_banking_app.repository.CardRepository;
import ru.pet.my_banking_app.repository.UserRepository;
import ru.pet.my_banking_app.service.UserService;

@Service
@Transactional(readOnly = true)
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
    @Transactional
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
        return createdUser;
    }

}
