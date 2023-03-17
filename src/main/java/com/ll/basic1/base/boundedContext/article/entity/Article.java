package com.ll.basic1.base.boundedContext.article.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id //PRIMARY KEY
    @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
    private long id;
    @CreatedDate
    private LocalDateTime createDate; // 데이터 생성 날짜

    @LastModifiedBy
    private LocalDateTime modifyDate; // 데이터 수정 날짜
    private String title;
    private String body;


}