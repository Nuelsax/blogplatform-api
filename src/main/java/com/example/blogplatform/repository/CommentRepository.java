package com.example.blogplatform.repository;

import com.example.blogplatform.models.Comment;
import com.example.blogplatform.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByPostPostId(Long postId);
    List<Comment> getAllByCommentMessageContains(String commentBody);
}
