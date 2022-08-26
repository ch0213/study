package com.example.restdocs;

import com.example.restdocs.application.ImageService;
import com.example.restdocs.dto.ImageResponse;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.io.File;

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

class ImageDocumentationV2 extends DocumentationV2 {
    @MockBean
    ImageService imageService;

    /**
     * request: multipart/form-data
     * response: header
     */
    @DisplayName("이미지를 업로드한다.")
    @Test
    void upload() {
        File imageFile = new File(ImageDocumentationV1.class.getClassLoader().getResource("static/images/pizza.png").getPath());

        when(imageService.save(any())).thenReturn(1L);

        RestAssured
                .given(spec).log().all()
                .filter(document("images/save/v2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParts(
                                partWithName("authorName").description("작가명"),
                                partWithName("size").description("크기"),
                                partWithName("price").description("가격"),
                                partWithName("country").description("나라"),
                                partWithName("image").description("이미지")),
                        responseHeaders(headerWithName("Location").description("위치"))
                ))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart(new MultiPartSpecBuilder("작가명").controlName("authorName").build())
                .multiPart(new MultiPartSpecBuilder(100).controlName("size").build())
                .multiPart(new MultiPartSpecBuilder(2_000_000).controlName("price").build())
                .multiPart(new MultiPartSpecBuilder("korea").controlName("country").build())
                .multiPart("image", imageFile, "image/png")
                .when().post("/images")
                .then().log().all().extract();
    }

    /**
     * request: query param
     * response: application/json
     */
    @DisplayName("이미지를 조회한다.")
    @Test
    void findImage() {
        when(imageService.findByName(any())).thenReturn(new ImageResponse("사진작품", "신짱구", 1_000_000));

        RestAssured
                .given(spec).log().all()
                .filter(document("images/find/v2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(parameterWithName("name").description("작가명")),
                        responseFields(
                                subsectionWithPath("name").description("작품명"),
                                subsectionWithPath("authorName").description("작가명"),
                                subsectionWithPath("price").description("가격"))
                ))
                .when().get("/images?name={name}", "신짱구")
                .then().log().all().extract();
    }
}
