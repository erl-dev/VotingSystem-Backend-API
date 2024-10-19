package com.votingsystem.votingsystembackend.ServiceImpl;

import com.votingsystem.votingsystembackend.DTO.RegisterReq;
import com.votingsystem.votingsystembackend.Entity.RoleEntity;
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


        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = new RoleEntity();

        userEntity.setFirstName(registerReq.getFirstName());
        userEntity.setLastName(registerReq.getLastName());
        userEntity.setEmail(registerReq.getEmail());
        userEntity.setRole(roleEntity);


        // implementing the bcrypt for passwords
        String encryptedPass = bcrypt.encode(registerReq.getPassword());
        userEntity.setPassword(encryptedPass);


        userRepository.save(userEntity);

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
