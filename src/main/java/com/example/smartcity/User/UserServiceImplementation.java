package com.example.smartcity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    //Repository injection
    private final UserRepository userRepository;




    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void createUser(User userRequest) {


    }

    public void updateUser() {
        //todo
    }

    public void deleteUser() {
        //todo
    }



}
