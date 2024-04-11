package ru.pet.my_banking_app.domen;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    private Integer number;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Card() {
    }

    public Card(Integer number, BigDecimal balance, User owner) {
        this.number = number;
        this.balance = balance;
        this.owner = owner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
