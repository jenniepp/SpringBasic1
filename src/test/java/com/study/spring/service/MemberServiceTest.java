package com.study.spring.service;

import com.study.spring.domain.Member;
import com.study.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//given-when-then test검증 : 조건이 주어지고 어떤상황일 때 이런 결과가 나온다.
class MemberServiceTest {
     /*
    우리의 test목적은 memberService에서 만들어진 코드와 인스턴스가 잘 작동하는지 보는 것인데
    이 클래스에서 memberRepository를 따로 만들어서 돌린다면 엄밀히 말하자며 memberService에서
    사용되는 인스턴스와 여기서 사용되는 인스턴스가 달라 다른 인스턴스를 가지고 테스트를 돌리는 것이라
    판단할 수 있다. 즉, 다른 결과가 도출될 가능성이 있다.
    */

    //선언 후
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //검증 들어가기 전에 객체 생성
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){ //각 test마다 의존성 제거
        memberRepository.clearStore();
    }

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