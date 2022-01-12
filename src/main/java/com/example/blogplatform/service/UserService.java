package com.example.blogplatform.service;

import com.example.blogplatform.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User loginUser(String email, String password);
    List<User> getAllUser();
    User getUserbyId(long id);
    ResponseEntity<User> deleteUser(long userId);
    User updateUser(User user, long id);
}
