package com.ugurcangursen.UserLoginRegister.dto;

public class UserDTO {


    private Long id;

    private String nameSurname;

    private String email;

    public UserDTO(Long id, String nameSurname, String email) {
        super();
        this.id = id;
        this.nameSurname = nameSurname;
        this.email = email;
    }

    public UserDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
