package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.Exception.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.pool.TypePool;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public User 회원수정(int id, User user){

        // 1. 영속화 : 1) get 함수 - 무조건 찾았다. 걱정마 2) orElseThrow 함수 - 못찾았어 exception 발동시킬게
        User userEntity = userRepository.findById(id).orElseThrow(()->{
            return new CustomValidationApiException("찾을 수 없는 ID 입니다.");
        });


        // 2. 영속화된 오브젝트는 영속성 컨텍스트에 등록되어 수정 시 더티체킹을 통해 db에 자동으로 업데이트가 완료된다.
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);

        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }
}
