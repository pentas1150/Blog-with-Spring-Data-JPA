package com.jpa.blog.service;

import com.jpa.blog.domain.liked.Liked;
import com.jpa.blog.domain.post.Post;
import com.jpa.blog.domain.user.User;
import com.jpa.blog.repository.LikedRepository;
import com.jpa.blog.repository.PostRepository;
import com.jpa.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikedService {

    private final LikedRepository likedRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikedService(LikedRepository likedRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likedRepository = likedRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Liked insertLiked(Long postId, String userNickname) throws IllegalAccessException, IllegalStateException {
        User exUser = userRepository.findByUserNickname(userNickname).orElseThrow(IllegalAccessException::new);
        Post exPost = postRepository.findById(postId).orElseThrow(IllegalAccessException::new);

        likedRepository.findFirstByPostAndUser(exPost, exUser).ifPresent((l) -> {
            System.out.println("이미 좋아요 누름");
            throw new IllegalStateException();
        });

        return likedRepository.save(Liked.builder()
                .user(exUser)
                .post(exPost)
                .build());
    }

    @Transactional
    public boolean isLikedPost(Long postId, String userNickname) throws IllegalAccessException {
        User exUser = userRepository.findByUserNickname(userNickname).orElseThrow(IllegalAccessException::new);
        Post exPost = postRepository.findById(postId).orElseThrow(IllegalAccessException::new);

        return likedRepository.findFirstByPostAndUser(exPost, exUser).isPresent();
    }
}
