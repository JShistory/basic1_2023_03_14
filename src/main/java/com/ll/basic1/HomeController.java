package com.ll.basic1;

import com.ll.basic1.base.boundedContext.member.entity.Member;
import com.ll.basic1.base.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller 의 의미
// 개발자가 스프링부트에게 말한다.
// 아래 있는 HomeController 는 컨트롤러이다.
@Controller
public class HomeController {
    private int count;
    private final List<Person> peoples;

    private final MemberService memberService;

    public HomeController(MemberService memberService){
        count = -1;
        peoples = new ArrayList<>();
        this.memberService = memberService;


    }
    // @GetMapping("/home/main") 의 의미
    // 개발자가 스프링부트에게 말한다.
    // 만약에 /home/main 이런 요청이 오면 아래 메서드를 실행해줘
    @GetMapping("/home/main")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain(){
        return "안녕하세요.";
    }
    @GetMapping("/home/main2")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain2(){
        return "반갑습니다.";
    }
    @GetMapping("/home/main3")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain3(){
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showIncrease(){
        return " "+count++;
    }

    @GetMapping("/home/plus")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue="0")int a,@RequestParam(defaultValue="200")int b){
        return a+b;
    }

    @GetMapping("/home/addPerson")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String Addperson(@RequestParam String name,int age){
        Person p = new Person(name, age);
        peoples.add(p);
        return "%d번 사람이 추가되었습니다.".formatted(p.getId());
    }

    @GetMapping("/home/people")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public List<Person> showPeople(){
        return peoples;
    }

    @GetMapping("/home/removePerson")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String removePerson(@RequestParam int id){
        boolean removed = peoples.removeIf(person -> person.getId() == id);

        if(removed == false){
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }
        return "%d번 사람이 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String modifyPerson(int id, String name, int age){
        Person found = peoples.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if(found == null){
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }
        found.setName(name);
        found.setAge(age);
        return "%d번 사람이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp){
        int countInCookie = 0;

        if(req.getCookies() != null){
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        int newCountInCookie = countInCookie + 1;
        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        return newCountInCookie;
    }

    @GetMapping("/home/user1")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public Member showUser1(){
        return memberService.findByUser("user1");
    }

}
