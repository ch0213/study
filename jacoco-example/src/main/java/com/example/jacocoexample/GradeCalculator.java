package com.example.jacocoexample;

import static com.example.jacocoexample.Grade.*;

public class GradeCalculator {
    private static final int MAX_SCORE = 100;
    private static final int MIN_SCORE = 0;

    private GradeCalculator() {

    }

    public static Grade calculate(int score) {
        if (score > MAX_SCORE || score < MIN_SCORE) {
            throw new IllegalArgumentException("점수는 " + MIN_SCORE + "~" + MAX_SCORE + "점 사이여야 합니다.");
        }

        if (score >= 90) {
            return A;
        }

        if (score >= 80) {
            return B;
        }

        if (score >= 70) {
            return C;
        }

        if (score >= 60) {
            return D;
        }

        return F;
    }
}
