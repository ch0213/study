package com.example.jacocoexample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GradeCalculatorTest {
    @DisplayName("입력받은 점수로 성적을 계산한다.")
    @CsvSource(value = {"100:A", "90:A", "80:B", "70:C", "60:D", "50:F"}, delimiter = ':')
    @ParameterizedTest
    void calculate(int score, Grade grade) {
        assertThat(GradeCalculator.calculate(score)).isEqualTo(grade);
    }

    @DisplayName("점수는 0~100점이어야 한다.")
    @ValueSource(ints = {-1, 101})
    @ParameterizedTest
    void calculateFail(int score) {
        assertThatThrownBy(() -> GradeCalculator.calculate(score))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
