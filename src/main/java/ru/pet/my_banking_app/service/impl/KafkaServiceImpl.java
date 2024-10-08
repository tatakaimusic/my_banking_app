package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import ru.pet.my_banking_app.domen.kafka.Topic;
import ru.pet.my_banking_app.service.KafkaService;

@Service
public class KafkaServiceImpl implements KafkaService {

    private final KafkaSender<String, Object> kafkaSender;

    @Autowired
    public KafkaServiceImpl(KafkaSender<String, Object> kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @Override
    public void sendEmailConfirmation(String email) {
        sendEmail(email, Topic.EMAIL_CONFIRMATION);
    }

    @Override
    public void sendResetPasswordEmail(String email) {
        sendEmail(email, Topic.PASSWORD_RESTORE);
    }

    private void sendEmail(String email, Topic topic) {
        kafkaSender.send(
                Mono.just(
                        SenderRecord.create(
                                topic.toString().toLowerCase(),
                                0,
                                System.currentTimeMillis(),
                                String.valueOf(email),
                                email,
                                null
                        )
                )
        );
    }

}
