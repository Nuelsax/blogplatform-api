package com.example.blogplatform.service.impl;

import com.example.blogplatform.models.Post;
import com.example.blogplatform.repository.PostRepository;
import com.example.blogplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> optional = postRepository.findById(id);
        Post post = null;
        if(optional.isPresent()) {
            post = optional.get();
        } else {
            throw  new RuntimeException("Post Not Found");
        }
        return post;
    }

    @Override
    public List<Post> getPostByTitle(String title) throws IOException {
        return postRepository.getAllByPostTitleContains(title);

    }

    @Override
    public void deletePostById(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public Page<Post> getAllPosts(String text, int range) {
        Pageable postPageableList = PageRequest.of(0, range, Sort.by(text));
        return postRepository.findAll(postPageableList);
    }
}

