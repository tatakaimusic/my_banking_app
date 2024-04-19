package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pet.my_banking_app.domen.Card;
import ru.pet.my_banking_app.domen.exception.ResourceNotFoundException;
import ru.pet.my_banking_app.repository.CardRepository;
import ru.pet.my_banking_app.service.CardService;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card getById(Long id) {
        return cardRepository.getCardById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Card with id= " + id + " doesn't exist!")
                );
    }

}
