package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// 실제 데이터베이스와 연동하기 전, 어떤 기능들이 필요하진 틀을 정해둔다(인터페이스)
public interface MemberRepository {

    // 찾는 id가 없을 때, null이 반환되어 발생할 수 있는 에러를 방지하기 위해 Optional로 감싼다.
    Member save(Member member);
    Optional<Member> findById(long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}