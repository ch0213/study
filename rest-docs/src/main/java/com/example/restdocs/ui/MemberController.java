package com.example.restdocs.ui;

import com.example.restdocs.dto.MemberRequest;
import com.example.restdocs.dto.MemberResponse;
import com.example.restdocs.application.MemberService;
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

    /**
     * request: application/json
     * response: header - URI
     */
    @PostMapping("/members")
    public ResponseEntity<Void> createMember(@RequestBody MemberRequest request) {
        Long id = memberService.create(request.getName(), request.getAge());
        return ResponseEntity.created(URI.create("/members/" + id)).build();
    }

    /**
     * request: path, header
     * response: application/json
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.find(id));
    }
}
