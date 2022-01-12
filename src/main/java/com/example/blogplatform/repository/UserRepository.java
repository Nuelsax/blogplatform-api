package com.example.blogplatform.repository;

import com.example.blogplatform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select s from User s where s.email = ?1 and s.password = ?2")
    User getUserByEmailAndPassword(String email, String password);
}
