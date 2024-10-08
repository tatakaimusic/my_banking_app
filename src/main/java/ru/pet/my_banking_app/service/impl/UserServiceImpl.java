package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.my_banking_app.domen.PasswordRestore;
import ru.pet.my_banking_app.domen.User;
import ru.pet.my_banking_app.domen.exception.ConfirmationCodeExpiredException;
import ru.pet.my_banking_app.domen.exception.ResourceNotFoundException;
import ru.pet.my_banking_app.repository.EmailRedisRepository;
import ru.pet.my_banking_app.repository.UserRepository;
import ru.pet.my_banking_app.service.KafkaService;
import ru.pet.my_banking_app.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailRedisRepository emailRedisRepository;
    private final KafkaService kafkaService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            EmailRedisRepository emailRedisRepository,
            KafkaService kafkaService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailRedisRepository = emailRedisRepository;
        this.kafkaService = kafkaService;
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
    public void sendConfirmationEmail(String email) {
        if (userRepository.getUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException(
                    "User with this email already exist!"
            );
        }
        kafkaService.sendEmailConfirmation(email);
    }


    @Override
    @Transactional
    public User register(User user, String code) {
        String rightCode = emailRedisRepository.getCode(user.getEmail());
        if (rightCode == null) {
            throw new ConfirmationCodeExpiredException();
        }
        if (!code.equals(rightCode)) {
            throw new IllegalArgumentException(
                    "Code is incorrect!"
            );
        }
        emailRedisRepository.deleteCode(user.getEmail());


        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public void sendResetEmail(String email) {
        kafkaService.sendResetPasswordEmail(email);
    }

//    @Override
//    public void restorePassword(PasswordRestore passwordRestore) {
//
//    }

}
