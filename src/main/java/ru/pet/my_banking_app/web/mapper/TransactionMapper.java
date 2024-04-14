package ru.pet.my_banking_app.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.pet.my_banking_app.domen.Transaction;
import ru.pet.my_banking_app.web.dto.TransactionDTO;

@Mapper(componentModel = "spring")
@Component
public interface TransactionMapper extends Mappable<Transaction, TransactionDTO> {
}
