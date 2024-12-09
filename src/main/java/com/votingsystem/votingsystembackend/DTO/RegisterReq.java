package com.votingsystem.votingsystembackend.DTO;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class RegisterReq {
    @JsonAlias("FirstName")
    private String firstName;
    @JsonAlias("LastName")
    private String lastName;
    @JsonAlias("Email")
    private String email;
    @JsonAlias("Password")
    private String password;
    @JsonAlias("RoleId")
    private int roleId;
}
