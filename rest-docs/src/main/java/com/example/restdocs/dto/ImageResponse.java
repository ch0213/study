package com.example.restdocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageResponse {
    private String name;

    private String authorName;

    private int price;
}
