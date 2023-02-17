package com.app.service;

import com.app.model.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    private List<User> users;
    public UserService() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read the JSON file and extract the "user" array
            JsonNode rootNode = objectMapper.readTree(new File("src/main/resources/json/user.json"));
            JsonNode usersNode = rootNode.path("record").path("user");

            // Convert the "user" array to a List<User>
            users = objectMapper.convertValue(usersNode, new TypeReference<List<User>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return this.users;
    }

    public boolean validateUser(User inputUser) {
        User user = this.users.stream()
                .filter(u -> u.getUsername().equals(inputUser.getUsername()))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return false;
        }

        // Check if the Base64 encoded password matches the record in the list of users
        String encodedPassword = Base64.encodeBase64String(inputUser.getPassword().getBytes());
        System.out.println(encodedPassword + ", " + user.getPassword());

        if (!user.getPassword().equals(encodedPassword)) {
            return false;
        }

        // Login successful
        return true;
    }
}
