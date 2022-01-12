package com.example.blogplatform.service.impl;

import com.example.blogplatform.exception.ResourceNotFoundException;
import com.example.blogplatform.models.User;
import com.example.blogplatform.repository.UserRepository;
import com.example.blogplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        return userRepository.getUserByEmailAndPassword("email","password");
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Override
    public User getUserbyId(long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id :" + id));

    }

    @Override
    public User updateUser(User user, long id) {
        User exisitingUser = this.userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id :" + id));
        exisitingUser.setFirstName(user.getFirstName());
        exisitingUser.setLastName(user.getLastName());
        exisitingUser.setEmail(user.getEmail());
        System.out.println("user updated");
        return this.userRepository.save(exisitingUser);

    }

    @Override
    public ResponseEntity<User> deleteUser(long userId) {
        User exisitingUser = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id :" + userId));
        this.userRepository.delete(exisitingUser);
        return ResponseEntity.ok().build();
    }

}
