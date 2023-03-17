package com.ll.basic1.base.boundedContext.article.controller;

import com.ll.basic1.base.RsData;
import com.ll.basic1.base.boundedContext.article.entity.Article;
import com.ll.basic1.base.boundedContext.article.repository.ArticleRepository;
import com.ll.basic1.base.boundedContext.article.service.ArticleService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor // 필드 중에서 final 붙은것만 인자로 입력받는 생성자 생성
@RequestMapping("/article")//추후에 mapping할때 이 주소가 붙음
public class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("/write")
    @ResponseBody
    public RsData write(String title, String body) {
        Article article = Article
                .builder()
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article); // INSERT, or UPDATE

        // 위 코드는 아래 코드와 비슷한 의미이다.
        /*
       Article article2 = new Article();
       article2.setTitlte(title);
       article2.setBody(body);
       */

        return RsData.of("S-1", "1번 글이 생성되었습니다.", article);
    }
}