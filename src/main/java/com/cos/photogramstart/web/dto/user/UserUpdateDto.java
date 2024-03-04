package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name; // 필수
    @NotBlank
    private String password; //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;


    // 다 필수인게 아니므로 엔티티가 이렇게 만들어지면 위험함
    public User toEntity() {
        return User.builder()
                .name(name) // 필수값 -> Validation 체크 해줘야됨
                .password(password) // 필수값 -> Validation 체크 해줘야됨
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
