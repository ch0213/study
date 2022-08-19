package com.example.restdocs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long create(String name, int age) {
        if (memberRepository.existsByName(name)) {
            throw new IllegalArgumentException();
        }
        Member member = memberRepository.save(new Member(name, age));
        return member.getId();
    }

    public MemberResponse find(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return MemberResponse.from(member);
    }
}
