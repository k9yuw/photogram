package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String name; // 필수
    private String password; //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;


    // 다 필수인게 아니므로 엔티티가 이렇게 만들어지면 위험함
    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
