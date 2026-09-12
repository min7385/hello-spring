package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    // EntityManager: persist(), find() 등의 명령을 내리는 주체로, JPA를 사용하려면 주입 받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    /*
    * 전달받은 member 객체를 JPA 영속성 컨텍스트에 저장한다.
    * JPA가 객체 정보와 @Id 설정을 확인하여 자동으로 적절한 INSERT SQL을 생성하고 execution을 수행한다.
    */
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    // 조회할 Type과 id를 넘기면 JPA가 SELECT 쿼리를 만들어 객체를 가져온다.
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m Where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();   // JPQL 사용
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();  // JPQL 사용
    }
}
