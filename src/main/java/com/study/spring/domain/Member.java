package com.study.spring.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 데이터라는 어노테이션
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고객아이디 x 시스템 아이디
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
