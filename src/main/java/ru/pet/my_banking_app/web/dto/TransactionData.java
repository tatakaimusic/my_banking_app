package ru.pet.my_banking_app.web.dto;

import java.math.BigDecimal;

public class TransactionData {

    private Long cardIdFrom;
    private Long cardIdTo;
    private BigDecimal amount;

    public TransactionData(Long cardIdFrom, Long cardIdTo, BigDecimal amount) {
        this.cardIdFrom = cardIdFrom;
        this.cardIdTo = cardIdTo;
        this.amount = amount;
    }

    public Long getCardIdFrom() {
        return cardIdFrom;
    }

    public void setCardIdFrom(Long cardIdFrom) {
        this.cardIdFrom = cardIdFrom;
    }

    public Long getCardIdTo() {
        return cardIdTo;
    }

    public void setCardIdTo(Long cardIdTo) {
        this.cardIdTo = cardIdTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
