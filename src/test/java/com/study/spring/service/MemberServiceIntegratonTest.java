package com.study.spring.service;

import com.study.spring.domain.Member;
import com.study.spring.repository.MemberRepository;
import com.study.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //test실행 전 트랜잭션실행 - test 데이터 실행 후 트랜잭션 롤백 > 데이터베이스에 데이터 반영x
class MemberServiceIntegratonTest {

    //선언 후-객체생성하는게 정석 but test는 그냥 여기서만쓰고 끝이니까 @Autowired로 객체선언해서 자주씀
    //필드기반 소스토리
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    //@Transactional이 각 test마다 의존성제거 해주므로 필요 없다.
//    @AfterEach
//    public void afterEach(){ //각 test마다 의존성 제거
//        memberRepository.clearStore();
//    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(),findMember.getName());
     }
     //test는 예외상황체크가 더 중요하므로 중복회원예외test
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1); //문제없음
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2); //제대로 중복회원이 걸러진다면 여기서 오류발생
            fail(); //
        } catch (IllegalStateException e) {
            Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}