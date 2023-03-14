package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
class Person{
    private static int lastId;
    private int id;
    private String name;
    private int age;

    static{
        lastId = 0;
    }
    Person(String name, int age){
        this(++lastId,name,age);
    }

}