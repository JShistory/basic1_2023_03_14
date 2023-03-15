package com.ll.basic1.base.boundedContext.member.controller;


import com.ll.basic1.base.RsData;
import com.ll.basic1.base.boundedContext.member.entity.Member;
import com.ll.basic1.base.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password,HttpServletResponse resp){
        if( username == null && username.trim().length()==0){
            return RsData.of("F-3","username(을) 입력해주세요.");
        }
        if( password == null && password.trim().length()==0){
            return RsData.of("F-3","password(을)를 입력해주세요.");
        }
        RsData rsData = memberService.tryLogin(username,password);

        if ( rsData.isSuccess()){
            long memberId = (long) rsData.getData();
            resp.addCookie(new Cookie("loginedMemberId", memberId + ""));
        }
        return rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    });
        }

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }


    @GetMapping("/member/save")
    @ResponseBody
    public Member save(String username, String password){
        return memberService.save(username,password);
    }

    @GetMapping("/member/me")
    @ResponseBody
    public RsData me(HttpServletRequest req, HttpServletResponse resp){
        long loginedMemberId = 0;

        if(req.getCookies() == null){
            return RsData.of("F-1","로그인 후 이용해주세요.");
        }
        loginedMemberId = Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                .map(Cookie::getValue)
                .mapToInt(Integer::parseInt)
                .findFirst()
                .orElse(0);

        Member member = memberService.findByUserId(loginedMemberId);
        return RsData.of("S-1","당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));

    }
}
