package com.votingsystem.votingsystembackend.ServiceImpl;

import com.votingsystem.votingsystembackend.DTO.RegisterReq;
import com.votingsystem.votingsystembackend.Entity.RoleEntity;
import com.votingsystem.votingsystembackend.Entity.UserEntity;
import com.votingsystem.votingsystembackend.Repository.RoleRepository;
import com.votingsystem.votingsystembackend.Repository.UserRepository;
import com.votingsystem.votingsystembackend.Security.JwtUtil;
import com.votingsystem.votingsystembackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void addUser(RegisterReq registerReq) {
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
        roleEntity = roleRepository.findById(registerReq.getRoleId()); // Adjust as necessary
        if (roleEntity == null) {
            throw new RuntimeException("Role not found");
        }

        userEntity.setRole(roleEntity);

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
            return jwtUtil.generateToken(user.getEmail(),user.getRole().getRoleName());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // You may want to retrieve roles and pass them to the UserDetails
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleId()));

        System.out.println("User roles for " + email + ": " + authorities); // Log the roles

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
