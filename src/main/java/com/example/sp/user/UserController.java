package com.example.sp.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 사용자 등록 폼 화면
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    // 사용자 등록 처리
    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/register"; // 입력값 검증 실패 시 다시 폼 반환
        }

        try {
            userService.save(user);
            model.addAttribute("successMessage", "User registered successfully!");
            return "user/login"; // 등록 후 로그인 화면으로 이동
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error occurred during registration.");
            return "user/register";
        }
    }

    // 로그인 화면 렌더링
    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }
}
