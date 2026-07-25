package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// ctrl + shift + T: 테이스 케이스 생성
public class MemberService {

    // 기존: MemberService가 MemoryMemberRepository를 직접 생성하게 함.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 신규: 의존성 주입 가능하게 변경함.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */
    public long join(Member member){

        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
     * 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
