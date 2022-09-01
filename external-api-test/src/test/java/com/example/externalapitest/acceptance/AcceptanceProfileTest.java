package com.example.externalapitest.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AcceptanceProfileTest extends FakeApiAcceptanceTest {
    @Value("${secret-key}")
    String secretKey;

    @Value("${hello}")
    String hello;

    @Value("${test-hello}")
    String testHello;

    @Value("${acceptance-test-hello}")
    String acceptanceTestHello;

    @DisplayName("설정 적용 확인")
    @Test
    void checkFileName() {
        assertAll(
                () -> assertThat(secretKey).isEqualTo("secret-key"),
                () -> assertThat(hello).isEqualTo("hello default"),
                () -> assertThat(testHello).isEqualTo("hello test"),
                () -> assertThat(acceptanceTestHello).isEqualTo("hello acceptance test")
        );
    }
}
