package com.example.sp.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository; // DB 작업을 위한 Repository

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화

    // 사용자 등록 메서드
    public void save(User user) {
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // DB 저장
        userRepository.save(user);
    }

    // 로그인 인증 메서드
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true; // 인증 성공
        }
        return false; // 인증 실패
    }
}
