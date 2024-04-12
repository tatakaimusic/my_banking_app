package ru.pet.my_banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pet.my_banking_app.domen.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
