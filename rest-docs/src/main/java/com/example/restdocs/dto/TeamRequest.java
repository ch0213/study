package com.example.restdocs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamRequest {
    private String teamName;

    private int maxHeadCount;

    public TeamRequest(String teamName, int maxHeadCount) {
        this.teamName = teamName;
        this.maxHeadCount = maxHeadCount;
    }
}
