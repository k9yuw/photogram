package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data // Getter Setter 만들어주는 어노테이션
public class SignupDto {

    @Max(20)
    private String username;

    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    // DB로 보내줘야 함
    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
