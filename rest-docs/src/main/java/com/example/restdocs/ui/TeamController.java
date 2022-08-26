package com.example.restdocs.ui;

import com.example.restdocs.application.TeamService;
import com.example.restdocs.dto.TeamRequest;
import com.example.restdocs.dto.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    /**
     * request: application/x-www-form-urlencoded
     */
    @PostMapping("/teams")
    public ResponseEntity<Void> createTeam(TeamRequest request) {
        Long id = teamService.createTeam(request);
        return ResponseEntity.created(URI.create("/teams/" + id)).build();
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity<TeamResponse> findTeam(@PathVariable Long teamId) {
        TeamResponse team = teamService.findById(teamId);
        return ResponseEntity.ok(team);
    }
}
