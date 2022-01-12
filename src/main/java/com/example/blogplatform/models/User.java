package com.example.blogplatform.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Newblogusers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(name = "firstName", nullable = false)
    protected String firstName;
    @Column(name = "lastName", nullable = false)
    protected String lastName;
    @Column(name = "email", nullable = false, unique = true)
    protected String email;
    @Column(name = "phoneNumber", nullable = false, unique = true)
    protected String phoneNumber;
    protected String dateOfBirth;
    @Column(name = "password", nullable = false)
    protected String password;
}
