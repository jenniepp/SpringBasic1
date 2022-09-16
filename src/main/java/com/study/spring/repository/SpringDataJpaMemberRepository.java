package com.study.spring.repository;

import com.study.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JPA는 JpaRepository를 받아야한다. JpaRepository<key:데이터클래스,type:기준데이터타입>
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {

    @Override
    Optional<Member> findByName(String name);

    /*인터페이스인데 구현체랑 없어도 되나? 된다!
    스프링데이터jpa가 JpaRepository써주면 자동으로 데이터 빈을 생성등록해준다.*/
}
