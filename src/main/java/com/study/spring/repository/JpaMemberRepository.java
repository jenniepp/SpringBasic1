package com.study.spring.repository;

import com.study.spring.domain.Member;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private  final EntityManager em; //JPA는 EntityManager로 모두 작동한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist : 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> resultList = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return resultList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> resultList = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return resultList;
    }
}
//JPA를 쓰려면 항상 트랜잭션이 있어야한다. 서비스에 @Transaction
//jpa는 join할 때 모든 데이터 처리가 트랜잭션 내부에서 되야한다.
