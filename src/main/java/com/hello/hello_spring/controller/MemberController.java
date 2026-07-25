package com.hello.hello_spring.controller;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 선택 시 GET 요청 받아 html 반환
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // MemberForm 객체를 생성하고, name의 값을 바인딩
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member.getName() = " + member.getName());

        // join() 메서드를 호출하여 실제 회원 데이터 저장
        memberService.join(member);

        return "redirect:/";
    }
}
