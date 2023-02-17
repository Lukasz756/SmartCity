package com.example.smartcity.User;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-controller")

public class UserController {
    //service injection
    private final UserServiceImplementation userServiceImplementation;
    private final JwtService jwtService;

    @GetMapping("/users")
    public List<User> getAllExistingUsers() {

        return userServiceImplementation.getAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public ResponseEntity<String> sayHello(){



        return ResponseEntity.ok("Hello there!");
    }

    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<String> postUser(@RequestBody User userRequest){
        userServiceImplementation.createUser(userRequest);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<String> putUser(@PathVariable Long userId){
        userServiceImplementation.updateUser();
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(){
        userServiceImplementation.deleteUser();
        return new ResponseEntity<>("",HttpStatus.OK);
    }

}
