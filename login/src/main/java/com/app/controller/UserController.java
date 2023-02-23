package com.app.controller;

import com.app.model.User;

import com.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> loginUser(@RequestBody User request) {
        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        if(userService.validateUser(request)) {
            return ResponseEntity.status(HttpStatus.OK).body(request);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(request);
    }
}

