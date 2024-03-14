package com.cos.photogramstart.domain.user;

import java.util.*;
import com.cos.photogramstart.domain.image.Image;
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
@Entity // DB에 테이블 생성
public class User {


    @Id // primary key 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 DB를 따라감 - MYSQL 같은 경우 autoincrement
    private int id;

    @Column(unique = true) // id 중복방지
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;

    private LocalDateTime createDate;
    private String role;

    // 양방향 매핑 - 나는 연관관계의 주인이 아니니 테이블에 컬럼을 만들지 마라는 뜻
    // user을 select 할 때 해당 user id로 등록된 image들을 ?
    // Lazy = User을 select 할 때 해당 User id로 등록된 image들을 가져오지말고, 대신 getImages()의 image들이 호출될 때 가져와.
    // Eager = User을 select 할 때 해당 USer id로 등록된 image들을 전부 join해서 가져와.
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Image> images;

    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
