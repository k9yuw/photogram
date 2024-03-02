package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션 없어도 JpaRepository 상속 시 IoC 등록이 자동으로 됨
public interface UserRepository extends JpaRepository<User, Integer> {

}
