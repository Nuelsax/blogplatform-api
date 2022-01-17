package com.example.blogplatform.service;

import com.example.blogplatform.models.Comment;
import com.example.blogplatform.models.Post;

import java.io.IOException;
import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);
    Comment getCommentById(Long id);
    List<Comment> getCommentByPostId(Long postId);
    void deleteCommentByPostPostId(int postId);
    void deleteAllByPostPostId(int postId);
    List<Comment> getCommentByCommentBody(String commentBody) throws IOException;
}
