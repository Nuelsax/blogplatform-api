package com.example.blogplatform.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogplatform.exception.ResourceNotFoundException;
import com.example.blogplatform.models.User;
import com.example.blogplatform.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testSaveUser() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setDateOfBirth("2020-03-01");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        assertSame(user, this.userServiceImpl.saveUser(user1));
        verify(this.userRepository).save((User) any());
        assertTrue(this.userServiceImpl.getAllUser().isEmpty());
    }

    @Test
    void testSaveUser2() {
        when(this.userRepository.save((User) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.saveUser(user));
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testLoginUser() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        when(this.userRepository.getUserByEmailAndPassword((String) any(), (String) any())).thenReturn(user);
        assertSame(user, this.userServiceImpl.loginUser("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).getUserByEmailAndPassword((String) any(), (String) any());
        assertTrue(this.userServiceImpl.getAllUser().isEmpty());
    }

    @Test
    void testLoginUser2() {
        when(this.userRepository.getUserByEmailAndPassword((String) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class,
                () -> this.userServiceImpl.loginUser("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).getUserByEmailAndPassword((String) any(), (String) any());
    }

    @Test
    void testGetAllUser() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUser = this.userServiceImpl.getAllUser();
        assertSame(userList, actualAllUser);
        assertTrue(actualAllUser.isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    void testGetAllUser2() {
        when(this.userRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.getAllUser());
        verify(this.userRepository).findAll();
    }

    @Test
    void testGetUserbyId() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userServiceImpl.getUserbyId(123L));
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userServiceImpl.getAllUser().isEmpty());
    }

    @Test
    void testGetUserbyId2() {
        when(this.userRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.getUserbyId(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testGetUserbyId3() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.getUserbyId(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testGetUserbyId4() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userServiceImpl.getUserbyId(123L));
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userServiceImpl.getAllUser().isEmpty());
    }

    @Test
    void testGetUserbyId5() {
        when(this.userRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.getUserbyId(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testGetUserbyId6() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.getUserbyId(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setDateOfBirth("2020-03-01");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setDateOfBirth("2020-03-01");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        assertSame(user1, this.userServiceImpl.updateUser(user2, 123L));
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userServiceImpl.getAllUser().isEmpty());
    }

    @Test
    void testUpdateUser2() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.save((User) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setDateOfBirth("2020-03-01");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.updateUser(user1, 123L));
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testUpdateUser3() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setDateOfBirth("2020-03-01");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.updateUser(user1, 123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(this.userRepository).delete((User) any());
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<User> actualDeleteUserResult = this.userServiceImpl.deleteUser(123L);
        assertNull(actualDeleteUserResult.getBody());
        assertEquals("<200 OK OK,[]>", actualDeleteUserResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(this.userRepository).delete((User) any());
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userServiceImpl.getAllUser().isEmpty());
    }

    @Test
    void testDeleteUser2() {
        User user = new User();
        user.setDateOfBirth("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        Optional<User> ofResult = Optional.of(user);
        doThrow(new ResourceNotFoundException("An error occurred")).when(this.userRepository).delete((User) any());
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.deleteUser(123L));
        verify(this.userRepository).delete((User) any());
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testDeleteUser3() {
        doNothing().when(this.userRepository).delete((User) any());
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.deleteUser(123L));
        verify(this.userRepository).findById((Long) any());
    }
}

