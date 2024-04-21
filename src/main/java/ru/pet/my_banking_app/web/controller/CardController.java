package ru.pet.my_banking_app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet.my_banking_app.domen.Currency;
import ru.pet.my_banking_app.service.CardService;
import ru.pet.my_banking_app.web.dto.CardDTO;
import ru.pet.my_banking_app.web.mapper.CardMapper;

@RestController
@RequestMapping("/api/cards")
@Validated
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;

    @Autowired
    public CardController(CardService cardService, CardMapper cardMapper) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @PostMapping("/create/{userId}/{currency}")
    public CardDTO create(
            @PathVariable Currency currency,
            @PathVariable Long userId
    ) {
        return cardMapper.toDto(
                cardService.create(currency, userId)
        );
    }

}
