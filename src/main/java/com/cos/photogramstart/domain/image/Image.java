package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption;
    private String postImageUrl; // 사진을 전송받아서 그 사진을 서버 속 특정 폴더에 저장하고, DB에 그 저장된 경로를 insert 할 것이다.

    @JoinColumn(name="userId")
    @ManyToOne
    private User user;

    // 이미지 좋아요 기능, 이미지 댓글 기능

    private LocalDateTime createDate;

    // DB에는 항상 언제 만들어졌는지 시간이 필요하다 !!!
    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
