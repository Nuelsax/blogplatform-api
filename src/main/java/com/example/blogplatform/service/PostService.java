package com.example.blogplatform.service;

import com.example.blogplatform.models.Post;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post savePost(Post post);
    List<Post> getAllPosts();
    Post getPostById(Long id);
    void deletePostById(Long id);
    List<Post> getPostByTitle(String title) throws IOException;
    Page<Post> getAllPosts(String text, int range);
}
