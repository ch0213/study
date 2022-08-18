package com.example.externalapitest.unit;

import com.example.externalapitest.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberTest {

    @DisplayName("회원 정보 수정 테스트")
    @Test
    void update() {
        Member member = new Member("테스트 유저", 25);

        member.update("유저 테스트", 26);

        assertAll(
                () -> assertThat(member.getName()).isEqualTo("유저 테스트"),
                () -> assertThat(member.getAge()).isEqualTo(26)
        );
    }
}
