package com.example.externalapitest.dto;

public class MemberResponse {
    private Long id;
    private String name;
    private int age;

    private MemberResponse() {

    }

    public MemberResponse(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static MemberResponse of(Long id, String name, int age) {
        return new MemberResponse(id, name, age);
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
