package ru.pet.my_banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pet.my_banking_app.domen.Transaction;
import ru.pet.my_banking_app.domen.exception.InsufficientFundsException;
import ru.pet.my_banking_app.repository.CardRepository;
import ru.pet.my_banking_app.repository.TransactionRepository;
import ru.pet.my_banking_app.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(
            CardRepository cardRepository,
            TransactionRepository transactionRepository
    ) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional(
            isolation = Isolation.REPEATABLE_READ
    )
    public Transaction doTransaction(
            Long cardFromId, Long cardToId, BigDecimal amount
    ) {
        if (Objects.equals(cardFromId, cardToId)) {
            throw new IllegalStateException("Cards must be different!");
        }
        BigDecimal balanceFrom = cardRepository.getBalanceByCardId(cardFromId);
        BigDecimal balanceTo = cardRepository.getBalanceByCardId(cardToId);
        if (balanceFrom.subtract(amount).compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }

        cardRepository.updateBalanceByCardId(cardFromId, balanceFrom.subtract(amount));
        cardRepository.updateBalanceByCardId(cardToId, balanceTo.add(amount));

        return transactionRepository.save(
                new Transaction(
                        cardFromId,
                        cardToId,
                        amount,
                        LocalDateTime.now()
                )
        );
    }


}
