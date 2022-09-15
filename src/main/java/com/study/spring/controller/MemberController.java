package com.study.spring.controller;

import com.study.spring.domain.Member;
import com.study.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //spring controller라는 통에 이 클래스가 들어감. =스프링빈 관리
public class MemberController {

    private final MemberService memberService;

    @Autowired //생성자를 이용하여 의존 관계 주입
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        /* html ${} : model안의 값을 꺼내는 것.
        members라는 리스트 안에 데이터를 담아둠 이걸 members라는 이름으로 이동시키려고 model에 담는다.*/
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
