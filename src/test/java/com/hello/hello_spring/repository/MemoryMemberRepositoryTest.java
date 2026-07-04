package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메서드의 실행이 끝날 때마다 동작을 수행함
    // 테스트는 서로 순서와 관계없이 설계 되어야 함. 이를 위해 하나의 테스트가 끝날 때마다 공용 데이터들을 다시 비워줘야 함.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        /* repository.findById()의 값이 null 일 경우
        NosuchElementException 에러가 발생할 수 있기 때문에 .get() 대신에 orElseThrow()를 권장
         */
        Member result = repository.findById(member.getId()).orElseThrow();
        System.out.println("result = " + (result == member));
        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").orElseThrow();

        // 객체의 메모리 주소 비교
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
