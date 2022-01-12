package com.example.blogplatform.controller;

import com.example.blogplatform.models.Post;
import com.example.blogplatform.models.User;
import com.example.blogplatform.service.PostService;
import com.example.blogplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/blog/post")
public class PostController {
    private UserService userService;
    private PostService postService;
    @Autowired
    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public List<Post> showPost() {
       return postService.getAllPosts();
    }
    @PostMapping("/user/{id}")
    public String savePost(@RequestBody Post post, @PathVariable(value = "id") long userid) {
        User exisitingUser = userService.getUserbyId(userid);
          post.setUser(exisitingUser);
          post.setLocalDateTime(LocalDateTime.now());
          postService.savePost(post);
          return "post Successsfully saved";
        }
    @GetMapping("/{id}")
    public Post showPostById(@PathVariable(value = "id") Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/search/{title}")
    public List<Post> showPostByTitle(@PathVariable(value = "title") String title) throws IOException {
        return postService.getPostByTitle(title);
    }


}
