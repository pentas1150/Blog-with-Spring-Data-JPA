package com.jpa.blog.domain.post;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostResponseDTO {

    private String title;
    private String content;
    //user nickname
    private String author;
    private int liked;
    private boolean isLiked;
    private LocalDateTime createdAt;
}
