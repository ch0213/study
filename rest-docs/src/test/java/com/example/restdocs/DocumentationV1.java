package com.example.restdocs;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("test")
public class DocumentationV1 {
    private static final String HOST = "docs.api.com";

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        RestAssuredMockMvc.webAppContextSetup(context, documentationConfiguration(restDocumentation));
    }

    /**
     * port를 제거할 수도 있다.
     * return preprocessRequest(modifyUris().removePort(), prettyPrint());
     *
     * port를 변경할 수도 있다.
     * return preprocessRequest(modifyUris().host(HOST).port(443), prettyPrint());
     */
    protected static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(modifyUris().host(HOST).removePort(), prettyPrint());
    }

    protected static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }
}
