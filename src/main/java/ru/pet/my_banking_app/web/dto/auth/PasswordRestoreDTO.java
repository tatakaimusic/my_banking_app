package ru.pet.my_banking_app.web.dto.auth;

import jakarta.validation.constraints.NotNull;

public class PasswordRestoreDTO {

    @NotNull(message = "Restore code must be not null!")
    private String code;

    @NotNull(message = "Password must be not null!")
    private String password;

    public PasswordRestoreDTO(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
