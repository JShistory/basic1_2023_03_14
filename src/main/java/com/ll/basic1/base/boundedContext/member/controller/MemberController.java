package com.ll.basic1.base.boundedContext.member.controller;


import com.ll.basic1.base.Rq;
import com.ll.basic1.base.RsData;
import com.ll.basic1.base.boundedContext.member.entity.Member;
import com.ll.basic1.base.boundedContext.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/member/login")
    public String showLogin() {
        return "user/member/login";
    }

    @PostMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username(을)를 입력해주세요.");
        }
        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-4", "password(을)를 입력해주세요.");
        }
        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            Member member = (Member) rsData.getData();
            rq.setSettion("loginedMemberId", member.getId());
        }
        return rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout() {
        boolean cookieRemoved = rq.removeCookie("loginedMemberId");

        if (cookieRemoved == false) {
            return RsData.of("S-2", "이미 로그아웃 상태입니다.");
        }
        return RsData.of("S-1", "로그아웃 되었습니다.");
    }



    @GetMapping("/member/save")
    @ResponseBody
    public Member save(String username, String password){
        return memberService.save(username,password);
    }

    @GetMapping("/member/me")
    public String showMe(Model model) {
        long loginedMemberId = rq.getLoginedMemberId();

        Member member = memberService.findById(loginedMemberId);

        model.addAttribute("member",member);
        return "user/member/me";
    }
}
