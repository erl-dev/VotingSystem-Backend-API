package com.votingsystem.votingsystembackend.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private String message;
    private String email;
    private String token;
    private Long roleId;
}
