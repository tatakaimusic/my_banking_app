package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pet.my_banking_app.domen.Card;
import ru.pet.my_banking_app.domen.Currency;
import ru.pet.my_banking_app.domen.exception.ResourceNotFoundException;
import ru.pet.my_banking_app.repository.CardRepository;
import ru.pet.my_banking_app.service.CardService;
import ru.pet.my_banking_app.service.UserService;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;

    @Autowired
    public CardServiceImpl(
            CardRepository cardRepository, UserService userService
    ) {
        this.cardRepository = cardRepository;
        this.userService = userService;
    }

    public Card getById(Long id) {
        return cardRepository.getCardById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Card with id= " + id + " doesn't exist!")
                );
    }

    @Override
    public Card create(Currency currency, Long userId) {
        Card card = new Card();
        card.setCurrency(currency);
        card.setBalance(BigDecimal.ZERO);
        card.setNumber(generateNumber());
        card.setOwner(userService.getById(userId));
        return cardRepository.save(card);
//        cardRepository.assignUserToCard(created.getId(), userId);
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
