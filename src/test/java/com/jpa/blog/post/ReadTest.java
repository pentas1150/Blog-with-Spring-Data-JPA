package com.jpa.blog.post;

import com.jpa.blog.domain.post.Post;
import com.jpa.blog.domain.post.PostResponseDTO;
import com.jpa.blog.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReadTest {

    private final PostService postService;

    @Autowired
    public ReadTest(PostService postService) {
        this.postService = postService;
    }

    @Test
    @DisplayName("Read Test")
    public void readTest() {
        try {
            PostResponseDTO exPost = postService.findById(1L, "penta");

            System.out.println(exPost);

            assertTrue(true);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            assertTrue(false);
        }
    }
}
