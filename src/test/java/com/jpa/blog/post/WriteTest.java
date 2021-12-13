package com.jpa.blog.post;

import com.jpa.blog.domain.post.Post;
import com.jpa.blog.domain.post.PostRequestDTO;
import com.jpa.blog.domain.user.User;
import com.jpa.blog.service.PostService;
import com.jpa.blog.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Write Test")
public class WriteTest {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public WriteTest(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Test
    public void writeTest() {
        //given
        try {
            User author = userService.findByUserId("pentas");
            PostRequestDTO postRequestDTO = PostRequestDTO.builder()
                    .title("첫 게시글")
                    .content("첫 게시글입니다.")
                    .author(author)
                    .build();

            //when
            Post writtenPost = postService.insertPost(postRequestDTO);

            //then
            assertAll(
                    "Write Test",
                    () -> assertEquals(postRequestDTO.getTitle(), writtenPost.getTitle()),
                    () -> assertEquals(postRequestDTO.getContent(), writtenPost.getContent()),
                    () -> assertEquals(postRequestDTO.getAuthor().getUserNickname(), writtenPost.getUser().getUserNickname())
            );
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            assertTrue(false);
        }
    }
}
