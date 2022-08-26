package com.example.restdocs;

import com.example.restdocs.application.MemberService;
import com.example.restdocs.dto.MemberResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

class MemberDocumentationV2 extends DocumentationV2 {
    @MockBean
    MemberService memberService;

    /**
     * request: application/json
     * response: header
     */
    @DisplayName("회원 가입을 한다.")
    @Test
    void signup() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "이름");
        params.put("age", 20);

        when(memberService.create("이름", 20)).thenReturn(1L);
        when(memberService.find(1L)).thenReturn(new MemberResponse(1L, "이름", 20));

        RestAssured
                .given(spec).log().all()
                .filter(document("members/signup/v2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                subsectionWithPath("name").description("이름"),
                                subsectionWithPath("age").description("나이")),
                        responseHeaders(
                                headerWithName("Location").description("위치"))
                ))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/members")
                .then().log().all().extract();
    }

    /**
     * request: path param
     * response: application/json
     */
    @DisplayName("회원을 조회한다.")
    @Test
    void find() {
        when(memberService.find(any())).thenReturn(new MemberResponse(1L, "홍길동", 25));

        RestAssured
                .given(spec).log().all()
                .filter(document("members/find/v2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("memberId").description("회원 아이디")),
                        responseFields(
                                subsectionWithPath("id").description("아이디"),
                                subsectionWithPath("name").description("이름"),
                                subsectionWithPath("age").description("나이"))
                ))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/members/{memberId}", 1L)
                .then().log().all().extract();
    }
}
