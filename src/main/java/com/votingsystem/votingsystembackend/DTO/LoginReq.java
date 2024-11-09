package com.votingsystem.votingsystembackend.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class LoginReq {
    @JsonAlias("Email")
    private String email;
    @JsonAlias("Password")
    private String password;
}
