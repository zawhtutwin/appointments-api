package com.clinic.app.services;

import org.springframework.stereotype.Service;

import com.clinic.app.models.Ownable;
import com.clinic.app.models.User;

@Service
public class PolicyService {

    public boolean isAuthorized(User user, String action, Ownable resource) {
        	return resource.getCreatedBy().equals(user) || resource.getSharedWith().contains(user);
    }
}
