package com.example.blogplatform.controller;

import com.example.blogplatform.models.Comment;
import com.example.blogplatform.models.Post;
import com.example.blogplatform.models.User;
import com.example.blogplatform.service.CommentService;
import com.example.blogplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/blog/post")
public class CommentController {
    private CommentService commentService;
    private PostService postService;
    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }




    @PostMapping("/{postId}/comments")
    public String saveComment(@RequestBody Comment comment, @PathVariable(value = "postId") long postId,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("This is the User" + user);
        Post post = postService.getPostById(postId);
        System.out.println(" Succesfully Got here oo");
        comment.setPost(post);
        System.out.println("Got here oo");
        String combody = comment.getCommentMessage().toLowerCase();
        comment.setUser(user);
        comment.setCommentMessage(combody);
        commentService.saveComment(comment);
        return "comment Successsfully saved";
    }
    @GetMapping("/{postId}/comments")
    public List<Comment> getAllCommentForAPost( @PathVariable(value = "postId") long postId) {
        return commentService.getCommentByPostId(postId);

    }

    @GetMapping("/search/comment/{commentBody}")
    public List<Comment> showPostByTitle(@PathVariable(value = "commentBody") String commentBody) throws IOException {
        String combody = commentBody.toLowerCase();
        return commentService.getCommentByCommentBody(combody);
    }

}
