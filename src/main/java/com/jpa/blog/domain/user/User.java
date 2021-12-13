package com.jpa.blog.domain.user;

import com.jpa.blog.domain.BaseEntity;
import com.jpa.blog.domain.liked.Liked;
import com.jpa.blog.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 15)
    private String userId;

    @Column(unique = true, nullable = false, length = 15)
    private String userNickname;

    @Column(nullable = false, length = 200)
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Liked> likeds = new ArrayList<>();
}
