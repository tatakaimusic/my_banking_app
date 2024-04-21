package ru.pet.my_banking_app.web.dto;

import jakarta.validation.constraints.NotNull;
import ru.pet.my_banking_app.web.dto.validation.OnUpdate;

public abstract class BaseEntityDTO {

    @NotNull(
            message = "Id must be not null!",
            groups = OnUpdate.class
    )
    private Long id;

    public BaseEntityDTO() {
    }

    public BaseEntityDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
