package com.example.restdocs.ui;

import com.example.restdocs.application.ImageService;
import com.example.restdocs.dto.ImageRequest;
import com.example.restdocs.dto.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    /**
     * request: multipart/form-data
     * response: header
     */
    @PostMapping("/images")
    public ResponseEntity<Void> saveImage(ImageRequest request) {
        Long id = imageService.save(request);
        return ResponseEntity.created(URI.create("/images/" + id)).build();
    }

    @GetMapping("/images")
    public ResponseEntity<ImageResponse> saveImage(@RequestParam String name) {
        ImageResponse image = imageService.findByName(name);
        return ResponseEntity.ok(image);
    }
}
