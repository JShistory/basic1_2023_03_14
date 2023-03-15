package com.ll.basic1.base.boundedContext.member.service;

import com.ll.basic1.base.RsData;
import com.ll.basic1.base.boundedContext.member.entity.Member;
import com.ll.basic1.base.boundedContext.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

//    public MemberService(){
//        memberRepository = new MemberRepository();
//    }
    public RsData tryLogin(String username, String password){
        Member member = memberRepository.findByUser(username);
        if(member == null){
            return RsData.of("F-2","%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }
        else if(!member.getPassword().equals(password)){
            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
        }


        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username),member.getId());
    }

    public Member save(String username, String password) {
        return memberRepository.save(username,password);
    }

    public Member findByUser(String username) {
        return memberRepository.findByUser(username);
    }

    public Member findByUserId(long loginedMemberId) {
        return memberRepository.findByUserId(loginedMemberId);
    }
}
