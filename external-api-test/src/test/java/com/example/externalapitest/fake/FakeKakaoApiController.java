package com.example.externalapitest.fake;

import com.example.externalapitest.dto.TokenResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("acceptance-test")
@RestController
public class FakeKakaoApiController {
    public static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @GetMapping("/oauth/token")
    public TokenResponse getToken() {
        return TokenResponse.from(token);
    }
}
