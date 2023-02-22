package com.MTN.rest.service;

import com.MTN.rest.Repository.HRUserRepository;
import com.MTN.rest.models.HRUser;
import org.springframework.stereotype.Service;

@Service
public class HRInfoService {
    private final HRUserRepository hrUserRepository;

    public HRInfoService(HRUserRepository hrUserRepository) {
        this.hrUserRepository = hrUserRepository;
    }

    public HRUser createAccount(HRUser hrUser) {
        String username = hrUser.getUsername();
        if (hrUserRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        return hrUserRepository.save(hrUser);
    }

    public HRUser findHRUserByUsername(String username) {
        return null;
    }

    public void createHRUser(HRUser hrUser) {
    }
}
