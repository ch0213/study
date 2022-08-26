package com.example.restdocs.application;

import com.example.restdocs.dto.TeamRequest;
import com.example.restdocs.dto.TeamResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {
    public Long createTeam(TeamRequest request) {
        // create team...
        return 1L;
    }

    public TeamResponse findById(Long teamId) {
        return new TeamResponse("팀 명", 0, null, null);
    }
}
