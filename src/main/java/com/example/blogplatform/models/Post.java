package com.example.blogplatform.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    @Column(name = "postTitle", nullable = false)
    private String postTitle;

    @Column(name = "postMessage", nullable = false)
    private String postMessage;
    @CreationTimestamp
    private LocalDateTime localDateTime;
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "userpostId",
            referencedColumnName = "id"
    )
    private User user;

}
