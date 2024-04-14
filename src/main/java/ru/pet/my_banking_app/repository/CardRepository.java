package ru.pet.my_banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pet.my_banking_app.domen.Card;

import java.math.BigDecimal;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = """
            SELECT balance FROM cards 
            WHERE id = :cardId 
            FOR UPDATE 
            """, nativeQuery = true)
    BigDecimal getBalanceByCardId(Long cardId);

    @Modifying
    @Query(value = """
              UPDATE cards
              SET balance = :balance
              WHERE id = :cardId        
            """, nativeQuery = true)
    void updateBalanceByCardId(Long cardId, BigDecimal balance);

}
