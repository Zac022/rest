package com.MTN.rest.controllers;

import com.MTN.rest.models.User;
import com.MTN.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;


    @PostMapping("/signing")
    public ResponseEntity<Map<String, Object>> signing(@RequestBody Map<String, String> requestData) {
        String username = requestData.get("username");
        String password = requestData.get("password");
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(getErrorResponse("Username and password are required."));
        }
        User user = userService.authenticate(username, password);
        if (user != null) {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("user", user);
            responseData.put("message", "User is successfully logged in");
            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getErrorResponse("Invalid username or password."));
        }
    }

    private Map<String, Object> getErrorResponse(String errorMessage) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        return errorResponse;
    }
}
