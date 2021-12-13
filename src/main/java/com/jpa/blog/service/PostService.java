package com.jpa.blog.service;

import com.jpa.blog.domain.post.Post;
import com.jpa.blog.domain.post.PostRequestDTO;
import com.jpa.blog.domain.post.PostResponseDTO;
import com.jpa.blog.domain.user.User;
import com.jpa.blog.repository.PostRepository;
import com.jpa.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post insertPost(PostRequestDTO postRequestDTO) {
        return postRepository.save(postRequestDTO.toEntity());
    }

    public List<PostResponseDTO> findAll(String requestedUserId) {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> PostResponseDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUserNickname())
                .liked(post.getLikeds().size())
                .isLiked(post.getLikeds().stream().anyMatch((l) -> l.getUser().getUserId().equals(requestedUserId)))
                .createdAt(post.getCreatedAt())
                .build()).collect(Collectors.toList());
    }

    public List<PostResponseDTO> findAllByUserNickname(String userNickname, String requestedUserId) throws IllegalAccessException {
        Optional<User> author = userRepository.findByUserNickname(userNickname);
        List<Post> posts = postRepository.findAllByUser(author.orElseThrow(IllegalAccessException::new));

        return posts.stream().map(post -> PostResponseDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUserNickname())
                .liked(post.getLikeds().size())
                .isLiked(post.getLikeds().stream().anyMatch((l) -> l.getUser().getUserId().equals(requestedUserId)))
                .createdAt(post.getCreatedAt())
                .build()).collect(Collectors.toList());
    }

    public List<PostResponseDTO> findAllByTitleContains(String keyword, String requestedUserId) {
        List<Post> posts = postRepository.findAllByTitleContains(keyword);

        return posts.stream().map(post -> PostResponseDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUserNickname())
                .liked(post.getLikeds().size())
                .isLiked(post.getLikeds().stream().anyMatch((l) -> l.getUser().getUserId().equals(requestedUserId)))
                .createdAt(post.getCreatedAt())
                .build()).collect(Collectors.toList());
    }

    public List<PostResponseDTO> findAllByContentContains(String keyword, String requestedUserId) {
        List<Post> posts = postRepository.findAllByContentContains(keyword);

        return posts.stream().map(post -> PostResponseDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUserNickname())
                .liked(post.getLikeds().size())
                .isLiked(post.getLikeds().stream().anyMatch((l) -> l.getUser().getUserId().equals(requestedUserId)))
                .createdAt(post.getCreatedAt())
                .build()).collect(Collectors.toList());
    }

    public PostResponseDTO findById(Long id, String requestedUserId) throws IllegalAccessException {
        Post post = postRepository.findById(id).orElseThrow(IllegalAccessException::new);

        return PostResponseDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUserNickname())
                .liked(post.getLikeds().size())
                .isLiked(post.getLikeds().stream().anyMatch((l) -> l.getUser().getUserId().equals(requestedUserId)))
                .createdAt(post.getCreatedAt())
                .build();
    }
}
