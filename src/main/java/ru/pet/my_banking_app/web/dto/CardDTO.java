package ru.pet.my_banking_app.web.dto;

import jakarta.validation.constraints.NotNull;
import ru.pet.my_banking_app.domen.Currency;
import ru.pet.my_banking_app.domen.User;
import ru.pet.my_banking_app.web.dto.validation.OnCreate;
import ru.pet.my_banking_app.web.dto.validation.OnUpdate;

import java.math.BigDecimal;

public class CardDTO extends BaseEntityDTO {

    @NotNull(
            message = "Card number must be not null!",
            groups = OnUpdate.class
    )
    private Long number;

    @NotNull(
            message = "Balance must be not null!",
            groups = OnUpdate.class
    )
    private BigDecimal balance;

    @NotNull(
            message = "Currency must be not null!",
            groups = {OnUpdate.class, OnCreate.class}
    )
    private Currency currency;

    @NotNull(
            message = "Owner must be not null!",
            groups = OnUpdate.class
    )
    private User owner;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
