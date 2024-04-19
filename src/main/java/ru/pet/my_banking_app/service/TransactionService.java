package ru.pet.my_banking_app.service;

import ru.pet.my_banking_app.domen.Currency;
import ru.pet.my_banking_app.domen.Transaction;

import java.math.BigDecimal;

public interface TransactionService {

    Transaction doTransaction(
            Long cardFromId, Long cardToId, BigDecimal amount, Currency currency
    );

}
