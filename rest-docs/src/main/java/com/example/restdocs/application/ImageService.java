package com.example.restdocs.application;

import com.example.restdocs.dto.ImageRequest;
import com.example.restdocs.dto.ImageResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {
    public Long save(ImageRequest request) {
        // save image...
        return 1L;
    }

    public ImageResponse findByName(String name) {
        return new ImageResponse(name, "신짱구", 1_000_000);
    }
}
