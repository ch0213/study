package com.example.externalapitest.ui;

import com.example.externalapitest.dto.MemberResponse;
import com.example.externalapitest.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@RestController
@RequiredArgsConstructor
public class TestController {

    @Value("${kakao.service.domain}")
    private String url;

    private final RestTemplate restTemplate;

    @PostMapping("/login")
    public TokenResponse login() {
        try {
            return restTemplate
                    .exchange(url + "/oauth/token", GET, null, TokenResponse.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException();
        }
    }

    @GetMapping("/members/{id}")
    public MemberResponse getMemberInfo() {
        return MemberResponse.of(1L, "테스트 유저", 25);
    }
}
