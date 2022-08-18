package com.example.externalapitest.domain;

public class Member {
    private Long id;
    private String name;
    private int age;

    protected Member() {

    }

    public Member(String name, int age) {
        this(null, name, age);
    }

    public Member(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void update(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
