package com.study.spring.repository;

import com.study.spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//다른애들을 가져다 쓰지 않기 때문에 public쓰지 않아도됨
//test를 하나 하고 나면 클리어되도록 레파지토리를 깨끗하게 지워주는 코드를 넣어야한다.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        //하나의 메소드가 끝날때마다 작동될 메소드 @AfterEach
        //test의 메소드는 각각 의존관계없이 설계되어야한다. 그래서 이 내용을 추가해주는 것이 좋다.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //test코드에서는 바로 .get()으로 값을 꺼내자
        //우리가 확인하고 싶은거 = 위의 두 값이 같은지

        // System.out.println("(result==member = " + (result == member));
        //이렇게 해도 확인가능하지만 계속 콘솔을 확인해야함. 비효율적 > Assertrion을 사용하자
        Assertions.assertEquals(member,result);
        //Assertions.assertThat(member)isEqualTo(result);
        //이런 코드도 있음. member가 result랑 똑같니? 이렇게 확인하는거.

    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertEquals(result,member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertEquals(result.size(),2);

    }
    /*여러명이서 개발을 하거나 코드가 만줄 넘어가게 되면 테스트코드 없이 개발하기는 불가능하다.
      프로젝트할 때 테스트 코드 부분도 꼭 사용해보고 공부하기*/

}
