package com.example.acceptancetestisolation.acceptance;

import com.example.acceptancetestisolation.MemberResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberAcceptanceTest extends AcceptanceTest {

    @DisplayName("회원을 생성한다.")
    @Test
    void save() {
        var response = 회원_생성_요청("신짱구", 25);

        var 회원 = 회원_조회_요청(response.header("Location")).as(MemberResponse.class);

        assertAll(
                () -> assertThat(회원.getId()).isEqualTo(1L),
                () -> assertThat(회원.getName()).isEqualTo("신짱구"),
                () -> assertThat(회원.getAge()).isEqualTo(25)
        );
    }

    @DisplayName("같은 이름의 회원을 생성할 수 없다.")
    @Test
    void saveSameMember() {
        var firstResponse = 회원_생성_요청("신짱구", 25);

        var response = 회원_생성_요청("신짱구", 26);

        assertThat(firstResponse.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private ExtractableResponse<Response> 회원_생성_요청(String name, int age) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("age", age);

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/members")
                .then().log().all().extract();
    }

    private ExtractableResponse<Response> 회원_조회_요청(String path) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(path)
                .then().log().all().extract();
    }
}
