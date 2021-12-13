package com.jpa.blog.domain.post;

import com.jpa.blog.domain.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostRequestDTO {

    private String title;
    private String content;
    private User author;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .user(author)
                .build();
    }
}
