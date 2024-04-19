package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.my_banking_app.domen.Card;
import ru.pet.my_banking_app.domen.Currency;
import ru.pet.my_banking_app.domen.Transaction;
import ru.pet.my_banking_app.domen.exception.InsufficientFundsException;
import ru.pet.my_banking_app.repository.CardRepository;
import ru.pet.my_banking_app.repository.TransactionRepository;
import ru.pet.my_banking_app.service.CardService;
import ru.pet.my_banking_app.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;
    private final CardService cardService;

    @Autowired
    public TransactionServiceImpl(
            CardRepository cardRepository,
            TransactionRepository transactionRepository,
            CardService cardService) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
    }

    @Transactional
    public Transaction doTransaction(
            Long cardFromId, Long cardToId,
            BigDecimal amount, Currency currency
    ) {
        if (Objects.equals(cardFromId, cardToId)) {
            throw new IllegalStateException("Cards must be different!");
        }
        Card from = cardService.getById(cardFromId);
        Card to = cardService.getById(cardToId);
        if (!isCorrectCurrency(from.getCurrency(), to.getCurrency())) {
            throw new IllegalStateException("Card must have same currency!");
        }
        BigDecimal balanceFrom = from.getBalance();
        BigDecimal balanceTo = to.getBalance();
        if (balanceFrom.compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        cardRepository.updateBalanceByCardId(cardFromId, balanceFrom.subtract(amount));
        cardRepository.updateBalanceByCardId(cardToId, balanceTo.add(amount));

        return transactionRepository.save(
                new Transaction(
                        cardFromId,
                        cardToId,
                        amount,
                        currency,
                        LocalDateTime.now()
                )
        );
    }

    private boolean isCorrectCurrency(Currency from, Currency to) {
        return from.compareTo(to) == 0;
    }

}
