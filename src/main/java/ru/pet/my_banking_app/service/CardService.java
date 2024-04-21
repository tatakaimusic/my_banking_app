package ru.pet.my_banking_app.service;

import ru.pet.my_banking_app.domen.Card;
import ru.pet.my_banking_app.domen.Currency;

public interface CardService {

    Card getById(Long id);

    Card create(Currency currency, Long userId);

}
