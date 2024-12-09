package com.votingsystem.votingsystembackend.Controller;

import com.votingsystem.votingsystembackend.DTO.RegisterReq;
import com.votingsystem.votingsystembackend.DTO.RegisterRes;
import com.votingsystem.votingsystembackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterRes> saveUser(@RequestBody RegisterReq registerReq) {
        RegisterRes response = new RegisterRes();
        try {
            userService.addUser(registerReq);

            response.setMessage("User registered successfully.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterReq registerReq) {
        try {
            // If login is successful, return the token
            String token = userService.login(registerReq);
            return ResponseEntity.ok("Login successful. Token:" + token);
        } catch (RuntimeException e) {
            // Catch the exception and return an appropriate error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
