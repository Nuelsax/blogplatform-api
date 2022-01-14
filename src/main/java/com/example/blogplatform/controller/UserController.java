package com.example.blogplatform.controller;

import com.example.blogplatform.models.User;
import com.example.blogplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/blog/users")
public class UserController {

    private UserService userService;
   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUser();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return this.userService.getUserbyId(userId);

    }
    @PostMapping
    public User RegisterUser(@RequestBody User user) {
        System.out.println("User Sucessfully added Added");
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
        return userService.updateUser(user,userId);
    }

    @PostMapping("/login/{id}")
    public String login(@RequestBody User user, @PathVariable("id") long userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User existingUser = userService.getUserbyId(userId);
        String email = existingUser.getEmail();
        String password = existingUser.getPassword();

         if( email.equals(user.getEmail()) && password.equals(user.getPassword())) {
             session.setAttribute("user", existingUser);
             return "succesfully logged in";
         } else {
             System.out.println("this is the existinguser email " + email);
             System.out.println("this is the exsitingUser password " + password);
             System.out.println("this is the email " + user.getEmail());
             System.out.println("this is the password " + user.getPassword());
             return "invalid credentials";
         }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        return userService.deleteUser(userId);
    }

}
