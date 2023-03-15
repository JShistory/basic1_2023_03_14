package com.ll.basic1.base.boundedContext.member.repository;

import com.ll.basic1.base.boundedContext.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
@AllArgsConstructor
@Repository
public class MemberRepository {
    private List<Member> members;
    public MemberRepository(){
        members = new ArrayList<>();
        members.add(new Member("user1","1234"));
        members.add(new Member("abc","1234"));
        members.add(new Member("test","1234"));
        members.add(new Member("love","1234"));
        members.add(new Member("like","1234"));
        members.add(new Member("giving","1234"));
        members.add(new Member("thanks","1234"));
        members.add(new Member("hello","1234"));
        members.add(new Member("good","1234"));
        members.add(new Member("peace","1234"));


    }
    public Member save(String username, String password){
        members.add(new Member(username,password));
        return null;
    }
    public Member findByUser(String username){
        return members.stream()
                .filter(m -> m.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
