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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

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
    // 로그인 폼 반환
    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login"; // user/login.html 반환
    }

    @PostMapping("/login")
    public String loginUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/login"; // 입력 검증 실패 시 다시 로그인 페이지 반환
        }

        // 로그인 성공 처리
        if (userService.authenticate(user.getUsername(), user.getPassword())) {
            return "redirect:/"; // 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "user/login"; // 로그인 실패 시 다시 로그인 페이지 반환
        }
    }


}
