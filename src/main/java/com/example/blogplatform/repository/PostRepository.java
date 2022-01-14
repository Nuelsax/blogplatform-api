package com.example.blogplatform.repository;

import com.example.blogplatform.models.Post;
import com.example.blogplatform.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select s from Post s where s.postTitle = ?1")
    Post getPostByEmail(String postTitle);

    List<Post> getAllByPostTitleContains(String postTitle);
    Page<Post> findAll(Pageable pageable);
}
