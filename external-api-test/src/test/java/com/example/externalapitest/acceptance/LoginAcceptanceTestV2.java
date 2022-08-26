package com.example.externalapitest.acceptance;

import com.example.externalapitest.dto.TokenResponse;
import com.example.externalapitest.utils.DatabaseCleanup;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.example.externalapitest.fake.FakeKakaoApiController.token;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("acceptance-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LoginAcceptanceTestV2 {
    @LocalServerPort
    int port;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();
    }

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
