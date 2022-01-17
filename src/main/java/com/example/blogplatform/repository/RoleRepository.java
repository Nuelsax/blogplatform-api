package com.example.blogplatform.repository;

import com.example.blogplatform.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository  extends JpaRepository<Role, Long> {
    @Query("select s from Role s where s.name = ?1")
    Role getRoleByName(String name);
}
