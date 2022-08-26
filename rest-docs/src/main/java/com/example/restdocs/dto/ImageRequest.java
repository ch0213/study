package com.example.restdocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ImageRequest {
    private String authorName;

    private int size;

    private int price;

    private String country;

    private MultipartFile image;
}
