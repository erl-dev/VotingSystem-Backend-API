package com.votingsystem.votingsystembackend.ServiceImpl;

import com.votingsystem.votingsystembackend.DTO.RegisterReq;
import com.votingsystem.votingsystembackend.Entity.UserEntity;
import com.votingsystem.votingsystembackend.Repository.UserRepository;
import com.votingsystem.votingsystembackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(RegisterReq registerReq){

        // check if a user with the same email already exists
        if (userRepository.findByEmail(registerReq.getEmail()) != null) {
            throw new RuntimeException("User with email " + registerReq.getEmail() + " already exists.");
        }

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


        UserEntity user = new UserEntity();

        user.setFirstName(registerReq.getFirstName());
        user.setLastName(registerReq.getLastName());
        user.setEmail(registerReq.getEmail());
        user.setRoleId(registerReq.getRoleId());

        // implementing the bcrypt for passwords
        String encryptedPass = bcrypt.encode(registerReq.getPassword());
        user.setPassword(encryptedPass);


        userRepository.save(user);

    }

    @Override
    public String login(RegisterReq registerReq) {
        // Autowire the BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Find the user by email
        UserEntity user = userRepository.findByEmail(registerReq.getEmail());

        // Check if user exists and if the password matches
        if (user != null && passwordEncoder.matches(registerReq.getPassword(), user.getPassword())) {
            return "Login Successful!";
        } else {
            return "Wrong Email or Password";
        }
    }




}
