package com.study.spring;

import com.study.spring.aop.TimeTraceAop;
import com.study.spring.repository.JpaMemberRepository;
import com.study.spring.repository.MemberRepository;
import com.study.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //JpaRepository사용했다면
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //jpa에서는 datasource가 아니라 EntityManager가 필요
   /* private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

//   private DataSource dataSource;
//
//   @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //memberservice,memberRepository가 빈객체 생성되어 스프링에 등록된다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }


    @Bean //만든 aop를 빈으로 등록하고 객체 사용하기
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
//    @Bean
//    public MemberRepository memberRepository() {
       // return new MemoryMemberRepository();
       // return new JdbcMemberRepository(dataSource);
       // return new JdbcTemplateMemberRepository(dataSource);
       // return new JpaMemberRepository(em);
//    }


}
