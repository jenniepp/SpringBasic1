package com.study.spring.service;

import com.study.spring.repository.JdbcMemberRepository;
import com.study.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

   private DataSource dataSource;

   @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //memberservice,memberRepository가 빈객체 생성되어 스프링에 등록된다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
       // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }


}
