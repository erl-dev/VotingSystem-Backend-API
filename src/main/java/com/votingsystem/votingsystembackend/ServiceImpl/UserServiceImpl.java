package com.votingsystem.votingsystembackend.ServiceImpl;

import com.votingsystem.votingsystembackend.DTO.UserDTO;
import com.votingsystem.votingsystembackend.Entity.User;
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
    public void addUser(UserDTO userDto){

        // check if a user with the same email already exists
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("User with email " + userDto.getEmail() + " already exists.");
        }

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        // implementing the bcrypt for passwords
        String encryptedPass = bcrypt.encode(userDto.getPassword());
        user.setPassword(encryptedPass);


        userRepository.save(user);

    }







}
