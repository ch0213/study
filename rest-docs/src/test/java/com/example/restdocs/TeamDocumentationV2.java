package com.example.restdocs;

import com.example.restdocs.application.TeamService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static com.example.restdocs.TeamFixture.팀_생성;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class TeamDocumentationV2 extends DocumentationV2 {
    @MockBean
    TeamService teamService;

    /**
     * request: application/x-www-form-urlencoded (query parameter도 동일)
     * response: header
     */
    @DisplayName("팀을 생성한다.")
    @Test
    void signup() {
        Map<String, Object> params = new HashMap<>();
        params.put("teamName", "바르셀로나");
        params.put("maxHeadCount", 20);

        when(teamService.createTeam(any())).thenReturn(1L);

        RestAssured
                .given(spec).log().all()
                .filter(document("teams/save/v2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("teamName").description("팀명"),
                                parameterWithName("maxHeadCount").description("팀원 수")),
                        responseHeaders(headerWithName("Location").description("위치"))
                ))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .formParams(params)
                .when().post("/teams")
                .then().log().all().extract();
    }

    /**
     * request: path param
     * response: application/json - array
     * reference: https://docs.spring.io/spring-restdocs/docs/current/reference/html5/#documenting-your-api-request-response-payloads-fields-json-field-paths
     */
    @DisplayName("팀을 조회한다.")
    @Test
    void findTeam() {
        when(teamService.findById(any())).thenReturn(팀_생성());

        RestAssured
                .given(spec).log().all()
                .filter(document("teams/find/v2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("teamId").description("팀 아이디")),
                        responseFields(
                                subsectionWithPath("name").description("팀 명"),
                                subsectionWithPath("headCount").description("인원 수"),
                                subsectionWithPath("members.[].id").description("아이디"),
                                subsectionWithPath("members.[].name").description("이름"),
                                subsectionWithPath("members.[].age").description("나이"),
                                subsectionWithPath("rooms.[]").description("회의실 명"))
                ))
                .when().get("/teams/{teamId}", 1L)
                .then().log().all().extract();
    }
}
