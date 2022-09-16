package com.study.spring.service;

import com.study.spring.domain.Member;
import com.study.spring.repository.MemberRepository;
import com.study.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //JPA를 쓰려면 항상 트랜잭션이 있어야한다.
public class MemberService {

    //0. 서비스를 만들기 위해선 멤버 리포지토리가 필요함
    // 멤버리파지토리를 직접 new로 생성하는 것이 아니라 외부에서 넣어주도록 만들자 ->의존성주입
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //1. 회원가입
    public Long join(Member member){
        //중복가입 x : optional로 감싸면 null값처리를 해주기때문에 이걸로 비교안하고 ifpresent를 쓸수있다.
        //result.orElseGet : 값이 있으면 꺼내고 없으면 디폴트값얼마를 출력해 아님 메소드를 써 이런.. 이것도 많이 쓰인다.
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        //코드를 더 간단하게 아래와 같이 표현 가능. 반환이 optional이기 때문에 바로 ifPresent가 들어갈 수 있다. 

        validateDuplicationMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //2-1. 전체회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //2-2. id값으로 데이터 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
