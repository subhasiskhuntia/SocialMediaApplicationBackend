package com.social.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String title;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private long totalLikes;
    @OneToOne
    private User createdBy;
    @OneToMany(mappedBy = "commentPost")
    private List<Comment> comments;
    @OneToMany(mappedBy = "likePost")
    private List<Likes> likes;
}
