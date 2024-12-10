package com.example.sp.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 필요시 사용자 정보를 확인하거나 조회하는 메서드를 정의 가능
}
