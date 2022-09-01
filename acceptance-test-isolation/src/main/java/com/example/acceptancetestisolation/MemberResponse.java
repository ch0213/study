package com.example.acceptancetestisolation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {
    private Long id;
    private String name;
    private int age;

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getAge());
    }
}
