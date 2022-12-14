package com.example.externalapitest.acceptance;

import com.example.externalapitest.dto.TokenResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.example.externalapitest.fake.FakeKakaoApiController.token;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 포트 문제로 인해 사용하지 않는 방식
 */
@Disabled
@ActiveProfiles("acceptance-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LoginAcceptanceTestV3 extends AcceptanceTest {
    @DisplayName("로그인 기능 테스트")
    @Test
    void login() {
        String 인증토큰 = 로그인_요청().as(TokenResponse.class).getToken();

        assertThat(인증토큰).isEqualTo(token);
    }

    private ExtractableResponse<Response> 로그인_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/login")
                .then().log().all().extract();
    }
}
