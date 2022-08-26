package com.example.restdocs;

import com.example.restdocs.application.MemberService;
import com.example.restdocs.dto.MemberResponse;
import com.example.restdocs.ui.MemberController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@WebMvcTest(controllers = {MemberController.class})
class MemberDocumentationV1 extends DocumentationV1 {
    @MockBean
    MemberService memberService;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
    }

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

        given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/members")
                .then().log().all()
                .status(HttpStatus.CREATED)
                .apply(document("members/signup/v1",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                subsectionWithPath("name").description("이름"),
                                subsectionWithPath("age").description("나이")),
                        responseHeaders(headerWithName("Location").description("위치")))).extract();
    }

    /**
     * request: path param
     * response: application/json
     */
    @DisplayName("회원을 조회한다.")
    @Test
    void find() {
        when(memberService.find(any())).thenReturn(new MemberResponse(1L, "홍길동", 25));

        given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/members/{memberId}", 1L)
                .then().log().all()
                .status(HttpStatus.OK)
                .apply(document("members/find/v1",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("memberId").description("회원 아이디")),
                        responseFields(
                                subsectionWithPath("id").description("아이디"),
                                subsectionWithPath("name").description("이름"),
                                subsectionWithPath("age").description("나이"))
                )).extract();
    }
}
