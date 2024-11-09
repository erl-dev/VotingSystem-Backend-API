package com.votingsystem.votingsystembackend.Service;

import com.votingsystem.votingsystembackend.DTO.LoginReq;
import com.votingsystem.votingsystembackend.DTO.LoginResponse;
import com.votingsystem.votingsystembackend.DTO.RegisterReq;

public interface UserService {
    void addUser(RegisterReq registerReq);
    LoginResponse login(LoginReq loginReq);

}
