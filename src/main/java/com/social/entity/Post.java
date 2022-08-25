package com.social.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "createdBy")
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
    @ManyToOne
    @JsonIgnore
    private User createdBy;
    @OneToMany(mappedBy = "commentPost",cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "likePost",cascade = CascadeType.ALL)
    private List<Likes> likes;
}
