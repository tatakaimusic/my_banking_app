package ru.pet.my_banking_app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet.my_banking_app.service.TransactionService;
import ru.pet.my_banking_app.web.dto.TransactionDTO;
import ru.pet.my_banking_app.web.dto.TransactionData;
import ru.pet.my_banking_app.web.mapper.TransactionMapper;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping
    public TransactionDTO doTransaction(
            @RequestBody TransactionData transactionData
    ) {
        return transactionMapper.toDto(
                transactionService.doTransaction(
                        transactionData.getCardIdFrom(),
                        transactionData.getCardIdTo(),
                        transactionData.getAmount()
                )
        );
    }

}
