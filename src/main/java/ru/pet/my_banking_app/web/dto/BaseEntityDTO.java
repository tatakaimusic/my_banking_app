package ru.pet.my_banking_app.web.dto;

public abstract class BaseEntityDTO {

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
