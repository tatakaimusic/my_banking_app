package ru.pet.my_banking_app.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.pet.my_banking_app.domen.Card;
import ru.pet.my_banking_app.web.dto.CardDTO;

@Mapper(componentModel = "spring")
@Component
public interface CardMapper extends Mappable<Card, CardDTO> {
}
