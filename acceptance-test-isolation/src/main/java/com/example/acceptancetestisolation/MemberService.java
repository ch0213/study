package com.example.acceptancetestisolation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(String name, int age) {
        if (memberRepository.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 이름입니다.");
        }
        Member member = memberRepository.save(new Member(name, age));
        return member.getId();
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return MemberResponse.from(member);
    }
}
