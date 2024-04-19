package ru.pet.my_banking_app.domen;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    private Long number;
    private BigDecimal balance;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public Card() {
    }

    public Card(Long id, Long number, BigDecimal balance, Currency currency, User owner) {
        super(id);
        this.number = number;
        this.balance = balance;
        this.currency = currency;
        this.owner = owner;
    }

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
