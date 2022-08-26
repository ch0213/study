package com.example.restdocs;

import com.example.restdocs.application.ImageService;
import com.example.restdocs.dto.ImageResponse;
import com.example.restdocs.ui.ImageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

@WebMvcTest(controllers = {ImageController.class})
class ImageDocumentationV1 extends DocumentationV1 {
    @MockBean
    ImageService imageService;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
    }

    /**
     * request: multipart/form-data
     * response: header
     */
    @DisplayName("이미지를 업로드한다.")
    @Test
    void upload() {
        Map<String, Object> params = new HashMap<>();
        File imageFile = new File(ImageDocumentationV1.class.getClassLoader().getResource("static/images/pizza.png").getPath());
        params.put("authorName", "작가명");
        params.put("size", 100);
        params.put("price", 2_000_000);
        params.put("country", "korea");

        when(imageService.save(any())).thenReturn(1L);

        given().log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .formParams(params)
                .multiPart("image", imageFile, "image/png")
                .when().post("/images")
                .then().log().all()
                .status(HttpStatus.CREATED)
                .apply(document("images/save/v1",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParts(partWithName("image").description("프로필 이미지")),
                        requestParameters(
                                parameterWithName("authorName").description("작가명"),
                                parameterWithName("size").description("사진 크기"),
                                parameterWithName("price").description("가격"),
                                parameterWithName("country").description("나라")),
                        responseHeaders(headerWithName("Location").description("위치")))).extract();
    }

    /**
     * request: query param
     * response: application/json
     */
    @DisplayName("이미지를 조회한다.")
    @Test
    void findImage() {
        when(imageService.findByName(any())).thenReturn(new ImageResponse("사진작품", "신짱구", 1_000_000));

        given().log().all()
                .queryParam("name", "신짱구")
                .when().get("/images")
                .then().log().all()
                .status(HttpStatus.OK)
                .apply(document("images/find/v1",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(parameterWithName("name").description("작가명")),
                        responseFields(
                                subsectionWithPath("name").description("작품명"),
                                subsectionWithPath("authorName").description("작가명"),
                                subsectionWithPath("price").description("가격"))
                )).extract();
    }
}
