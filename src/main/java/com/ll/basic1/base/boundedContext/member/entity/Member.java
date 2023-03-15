package com.ll.basic1.base.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Member {
    private final String username;
    private final String password;
}
