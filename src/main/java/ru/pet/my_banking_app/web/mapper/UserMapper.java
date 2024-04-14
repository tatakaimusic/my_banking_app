package ru.pet.my_banking_app.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.pet.my_banking_app.domen.User;
import ru.pet.my_banking_app.web.dto.UserDTO;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper extends Mappable<User, UserDTO> {
}
