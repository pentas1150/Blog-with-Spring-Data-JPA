package com.jpa.blog.repository;

import com.jpa.blog.domain.post.Post;
import com.jpa.blog.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);
    List<Post> findAllByTitleContains(String keyword);
    List<Post> findAllByContentContains(String keyword);
}
