package com.undefined.codeblooded.services;

import com.undefined.codeblooded.models.ApplicationUser;
import com.undefined.codeblooded.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ApplicationUser getUserFromEmail(String email){
        return userRepository.getByEmail(email);
    }

    public void saveToDb(ApplicationUser user){
        userRepository.save(user);
    }

}
