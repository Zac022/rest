package com.MTN.rest.controllers;

import com.MTN.rest.models.HRUser;
import com.MTN.rest.service.HRInfoService;
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
public class HRAccountController {
    @Autowired
    private HRInfoService hrInfoService;

    @PostMapping("/create-account")
    public ResponseEntity<Map<String, Object>> createAccount(@RequestBody HRUser hrUser) {
        HRUser existingUser = hrInfoService.findHRUserByUsername(hrUser.getUsername());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body(getErrorResponse("Username already exists"));
        }

        hrInfoService.createHRUser(hrUser);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", HttpStatus.CREATED.value());
        responseData.put("message", "User is successfully registered");
        return ResponseEntity.ok(responseData);
    }

    private Map<String, Object> getErrorResponse(String errorMessage) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        return errorResponse;
    }
}
