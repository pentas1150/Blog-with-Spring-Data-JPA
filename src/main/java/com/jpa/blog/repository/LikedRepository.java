package com.jpa.blog.repository;

import com.jpa.blog.domain.liked.Liked;
import com.jpa.blog.domain.post.Post;
import com.jpa.blog.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {

    Optional<Liked> findFirstByPostAndUser(Post post, User user);
}
