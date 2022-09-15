package com.study.spring.repository;

import com.study.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); //저장된 회원이 반환됨
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

    /*옵셔널로 감싸면 null처리가 유용하다.
    이렇게 하면 아이디만 들고올수도 네임만 들고올수도 전체를
    다 리스트로 들고올 수도 있다.*/

}
