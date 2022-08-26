package com.example.restdocs.dto;

import com.example.restdocs.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private int age;

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getAge());
    }
}
