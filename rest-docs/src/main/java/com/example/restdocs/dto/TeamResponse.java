package com.example.restdocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeamResponse {
    private String name;

    private int headCount;

    private List<MemberResponse> members;

    private List<String> rooms;
}
