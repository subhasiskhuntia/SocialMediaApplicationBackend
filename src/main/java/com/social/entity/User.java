package com.social.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column
    private LocalDate dob;
    @Column
    private String password;
    @Column(length = 50,unique = true)
    private String email;
    @Column(length = 20)
    private String phone;
    @Column(length = 1000)
    private String about;
    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "commentBy")
    private List<Comment> userComments;
    @OneToMany(mappedBy = "likeBy")
    private List<Likes> userLikes;

}
