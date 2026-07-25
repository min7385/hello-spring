package com.hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 루트 경로로 들어오는 요청 처리
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
