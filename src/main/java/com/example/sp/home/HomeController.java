package com.example.sp.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈 화면 반환
    @GetMapping("/")
    public String home() {
        return "home";  // home.html 반환
    }


}
