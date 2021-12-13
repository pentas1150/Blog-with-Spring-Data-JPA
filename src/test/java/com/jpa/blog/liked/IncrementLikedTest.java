package com.jpa.blog.liked;

import com.jpa.blog.domain.liked.Liked;
import com.jpa.blog.domain.post.PostResponseDTO;
import com.jpa.blog.service.LikedService;
import com.jpa.blog.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IncrementLikedTest {

    private final LikedService likedService;
    private final PostService postService;

    @Autowired
    public IncrementLikedTest(LikedService likedService, PostService postService) {
        this.likedService = likedService;
        this.postService = postService;
    }

    @Test
    @DisplayName("Increment Liked Test")
    public void incrementLikedTest() {
        try {
            PostResponseDTO beforePost = postService.findById(1L, "");
            Liked liked = likedService.insertLiked(1L, "");
            PostResponseDTO afterPost = postService.findById(1L, "");

            assertEquals(beforePost.getLiked() + 1, afterPost.getLiked());
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            assertTrue(false);
        } catch(IllegalStateException ise) {
            ise.printStackTrace();
            assertTrue(false);
        } catch(Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
