package com.example.jacocoexample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GradeCalculatorTest {
    private GradeCalculator gradeCalculator;

    @BeforeEach
    void setUp() {
        gradeCalculator = new GradeCalculator();
    }

    @CsvSource(value = {"100:A", "90:A", "80:B", "70:C", "60:D", "50:F"}, delimiter = ':')
    @ParameterizedTest
    void calculate(int score, Grade grade) {
        assertThat(gradeCalculator.calculate(score)).isEqualTo(grade);
    }

    @ValueSource(ints = {-1, 101})
    @ParameterizedTest
    void calculateFail(int score) {
        assertThatThrownBy(() -> gradeCalculator.calculate(score))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
