package ru.pet.my_banking_app.web.dto;

public class EmailConfirmationDTO {

    private UserDTO userDTO;
    private String code;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
