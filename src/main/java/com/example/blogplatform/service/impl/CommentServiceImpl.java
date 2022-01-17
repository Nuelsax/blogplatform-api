package com.example.blogplatform.service.impl;

import com.example.blogplatform.models.Comment;
import com.example.blogplatform.repository.CommentRepository;
import com.example.blogplatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
     @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentServiceImpl() {
        super();
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {
        return commentRepository.findCommentsByPostPostId(postId);
    }

    @Override
    public List<Comment> getCommentByCommentBody(String commentBody) throws IOException {
        return commentRepository.getAllByCommentMessageContains(commentBody);
    }

    @Override
    public void deleteCommentByPostPostId(int postId) {

    }

    @Override
    public void deleteAllByPostPostId(int postId) {

    }
}
