package com.hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // url 요청 시 hello-mvc에다가 파라미터(name)가 필요하고, 메서드에서 사용할 변수 factor
    // factor에 담긴 값을 username이라는 이름으로 넘겨준다.
    // hello-template.html 내부에서 Thymeleaf 문법으로 지정된 위치(${username})가 factor에 담긴 값으로 치환되어 렌더링 된다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String factor, Model model){
        model.addAttribute("username", factor);
        return "hello-template";
    }

    // 이 방식은 html이 아닌 문자 그대로 return 해준다.
    // @ResponseBody: http 통신에서 응답 body부에 직접 내용을 넣어주겠다.(html의 body가 아니다.)
    // 그래서 브라우저에서 소스코드 확인해보면 html이 아니다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    // API 방식
    // 객체를 클라이어트에 전달 가능한 형태로 반환(보통 JSON 형태)
    // HTTP 메시지 컨버터: 위 역할을 수행한다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // getter: 값을 가져오는 역할
        public String getName() {
            return name;
        }

        // setter: private인 name을 setName()메서드를 통해 접근할 수 있게한다.
        public void setName(String name) {
            this.name = name;
        }
    }
}
