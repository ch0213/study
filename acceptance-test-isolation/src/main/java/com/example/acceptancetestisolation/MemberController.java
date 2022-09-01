package com.example.acceptancetestisolation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Void> save(@RequestBody MemberRequest request) {
        Long memberId = memberService.save(request.getName(), request.getAge());
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable Long memberId) {
        MemberResponse member = memberService.findById(memberId);
        return ResponseEntity.ok(member);
    }
}
