package com.app.controller;

import com.app.model.User;

import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> loginUser(@RequestBody User request) {
        System.out.println(request.toString());
        if(userService.validateUser(request)) {
            return ResponseEntity.status(HttpStatus.OK).body(request);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(request);
    }
}

