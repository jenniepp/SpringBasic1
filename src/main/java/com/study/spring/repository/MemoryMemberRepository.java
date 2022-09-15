package com.study.spring.repository;

import com.study.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static long sequence = 0L;
    private static Map<Long, Member> store = new HashMap<>();
    //동시적 문제가 있을 수 있어 공유되는 변수일 땐 다른 타입 쓰는게 더 적합함 long보다 나은 선택지가 있음


    @Override
    public Member save(Member member) { //저장할 땐
        member.setId(++sequence); //sequence를 1씩 올려서 아이디에 값을 저장해라
        store.put(member.getId(), member); //store라는 맵에 아이디를 저장해라
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store에서 아이디를 꺼내라. > null일 가능성이 있으면 optional로 감싸서 반환하기
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

       //어떤 값을 리턴할거냐면 store라는 맵데이터에서 member.getName이 지금 매개변수인 name과 같으면 뽑아내라.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store라는 맵의 값들을 다 읽어서 ArrayList안에 넣어라
    }

    public void clearStore(){
        store.clear();
    }
}
