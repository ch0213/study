package com.example.externalapitest.acceptance;

import com.example.externalapitest.dto.MemberResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원 정보 조회기능 테스트")
    @Test
    void getMember() {
        MemberResponse 회원 = 회원_정보_요청(1L).as(MemberResponse.class);

        assertAll(
                () -> assertThat(회원.getId()).isEqualTo(1L),
                () -> assertThat(회원.getName()).isEqualTo("테스트 유저"),
                () -> assertThat(회원.getAge()).isEqualTo(25)
        );
    }

    private ExtractableResponse<Response> 회원_정보_요청(Long id) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/members/" + id)
                .then().log().all().extract();
    }
}
