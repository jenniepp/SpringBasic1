package com.study.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
aop : 실제 controller > service > repository 로 이어지는 사이사이에
가상의 프록시을 거쳐서 지나가도록 하는 기능.
시간측정로직/메소드호출될때마다 start와 end에 걸린다.
*/
@Aspect //aop의 필수 어노테이션

@Component
public class TimeTraceAop {

    @Around("execution(* com.study.spring..*(..))") //패키지 하위메뉴에 모두 적용시켜라 이건 다 다를 수 있다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        //시간로직
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed(); //다음 메소드로 진행

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
