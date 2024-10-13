package com.votingsystem.votingsystembackend.Service;

import com.votingsystem.votingsystembackend.DTO.RegisterReq;

public interface UserService {
    void addUser(RegisterReq registerReq);
    String login(RegisterReq registerReq);

}
