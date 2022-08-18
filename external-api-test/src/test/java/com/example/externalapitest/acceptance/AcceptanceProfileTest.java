package com.example.externalapitest.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AcceptanceProfileTest extends FakeApiAcceptanceTest {
    @Value("${secret-key}")
    String secretKey;

    @Value("${file-name}")
    String fileName;

    @Value("${test-file-name}")
    String testFileName;

    @Value("${acceptance-file-name}")
    String acceptanceFileName;

    @DisplayName("설정 파일명 확인")
    @Test
    void checkFileName() {
        assertAll(
                () -> assertThat(secretKey).isEqualTo("secret-key"),
                () -> assertThat(fileName).isEqualTo("application-test.yml"),
                () -> assertThat(testFileName).isEqualTo("application-test.yml"),
                () -> assertThat(acceptanceFileName).isEqualTo("application-acceptance-test.yml")
        );
    }
}
