package com.example.sp.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // username으로 사용자 검색
    User findByUsername(String username);
}
