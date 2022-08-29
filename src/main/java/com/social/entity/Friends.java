package com.social.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table( 
    uniqueConstraints=
        @UniqueConstraint(columnNames={"user1_id", "user2_id"})
)
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User  user1;
    @OneToOne
    private User user2;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(nullable = false,columnDefinition = "tinyint(1) default 0")
    private boolean accept;
}
