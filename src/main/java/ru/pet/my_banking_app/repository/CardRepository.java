package ru.pet.my_banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pet.my_banking_app.domen.Card;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = """
            SELECT * FROM cards 
            WHERE id = :cardId 
            FOR UPDATE 
            """, nativeQuery = true)
    Optional<Card> getCardById(Long cardId);

    @Modifying
    @Query(value = """
              UPDATE cards
              SET balance = :balance
              WHERE id = :cardId        
            """, nativeQuery = true)
    void updateBalanceByCardId(Long cardId, BigDecimal balance);

}
