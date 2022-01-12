package com.example.blogplatform.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogplatform.models.Post;
import com.example.blogplatform.models.User;
import com.example.blogplatform.repository.PostRepository;
import com.example.blogplatform.repository.UserRepository;
import com.example.blogplatform.service.PostService;
import com.example.blogplatform.service.UserService;
import com.example.blogplatform.service.impl.PostServiceImpl;
import com.example.blogplatform.service.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PostController.class})
@ExtendWith(SpringExtension.class)
class PostControllerTest {
    @Autowired
    private PostController postController;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;

    @Test
    void testSavePost() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(user));
        UserServiceImpl userService = new UserServiceImpl(userRepository);

        User user1 = new User();
        user1.setDateOfBirth("2020-03-01");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");

        Post post = new Post();
        post.setLocalDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostId(123L);
        post.setPostMessage("Post Message");
        post.setPostTitle("Dr");
        post.setUser(user1);
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.save((Post) any())).thenReturn(post);
        PostController postController = new PostController(userService, new PostServiceImpl(postRepository));

        User user2 = new User();
        user2.setDateOfBirth("2020-03-01");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");

        Post post1 = new Post();
        post1.setLocalDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostId(123L);
        post1.setPostMessage("Post Message");
        post1.setPostTitle("Dr");
        post1.setUser(user2);
        assertEquals("post Successsfully saved", postController.savePost(post1, 1L));
        verify(userRepository).findById((Long) any());
        verify(postRepository).save((Post) any());
        assertEquals(user2, post1.getUser());
    }

    @Test
    void testShowPost() throws Exception {
        when(this.postService.getAllPosts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/post");
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testShowPost2() throws Exception {
        when(this.postService.getAllPosts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/blog/post");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testShowPostById() throws Exception {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");

        Post post = new Post();
        post.setLocalDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostId(123L);
        post.setPostMessage("Post Message");
        post.setPostTitle("Dr");
        post.setUser(user);
        when(this.postService.getPostById((Long) any())).thenReturn(post);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/post/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"postId\":123,\"postTitle\":\"Dr\",\"postMessage\":\"Post Message\",\"localDateTime\":[1,1,1,1,1],\"user\":{\"id\""
                                        + ":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNumber\":\"4105551212\","
                                        + "\"dateOfBirth\":\"2020-03-01\",\"password\":\"iloveyou\"}}"));
    }

    @Test
    void testShowPostByTitle() throws Exception {
        when(this.postService.getPostByTitle((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/post/search/{title}", "Dr");
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

