package ru.pet.my_banking_app.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO extends BaseEntityDTO {

    private Long cardFrom;
    private Long cardTo;
    private BigDecimal amount;
    private LocalDateTime dateTime;

    public TransactionDTO(Long id, Long cardFrom, Long cardTo, BigDecimal amount, LocalDateTime dateTime) {
        super(id);
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public Long getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Long cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Long getCardTo() {
        return cardTo;
    }

    public void setCardTo(Long cardTo) {
        this.cardTo = cardTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
