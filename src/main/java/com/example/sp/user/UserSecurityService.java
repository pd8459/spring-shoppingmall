package com.example.sp.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username으로 사용자 찾기
        com.example.sp.user.User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // 권한 설정
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(user.getUsername())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 관리자 권한
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));  // 일반 사용자 권한
        }

        // UserDetails 반환
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
