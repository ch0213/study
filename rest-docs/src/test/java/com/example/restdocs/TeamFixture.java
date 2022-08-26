package com.example.restdocs;

import com.example.restdocs.dto.MemberResponse;
import com.example.restdocs.dto.TeamResponse;

import java.util.List;

public class TeamFixture {
    public static TeamResponse 팀_생성() {
        MemberResponse 홍길동 = new MemberResponse(1L, "홍길동", 25);
        MemberResponse 신짱구 = new MemberResponse(2L, "신짱구", 24);
        MemberResponse 김철수 = new MemberResponse(3L, "김철수", 26);
        MemberResponse 박훈이 = new MemberResponse(4L, "박훈이", 27);
        MemberResponse 이맹구 = new MemberResponse(5L, "이맹구", 33);

        return new TeamResponse("짱구는 못말려", 5,
                List.of(홍길동, 신짱구, 김철수, 박훈이, 이맹구), List.of("신형만", "봉미선", "나미리", "채성아"));
    }
}
