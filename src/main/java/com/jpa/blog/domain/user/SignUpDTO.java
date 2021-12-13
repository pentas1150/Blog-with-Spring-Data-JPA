package com.jpa.blog.domain.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignUpDTO {

    private String userId;
    private String userNickname;
    private String userPassword;

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .userNickname(this.userNickname)
                .userPassword(this.userPassword)
                .build();
    }
}
