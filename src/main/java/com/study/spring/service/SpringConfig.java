package com.study.spring.service;

import com.study.spring.repository.MemberRepository;
import com.study.spring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //memberservice,memberRepository가 빈객체 생성되어 스프링에 등록된다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
