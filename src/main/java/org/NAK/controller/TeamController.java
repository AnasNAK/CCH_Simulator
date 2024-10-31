package org.NAK.controller;

import jakarta.validation.Valid;
import org.NAK.DTO.Team.TeamCreateDTO;
import org.NAK.DTO.Team.TeamResponseDTO;
import org.NAK.services.contracts.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<TeamResponseDTO> getAllTeams() {
        return teamService.findAllTeams();
    }

    @GetMapping("/{id}")
    public TeamResponseDTO findTeamById(@PathVariable("id") Long id) {
        return teamService.findTeamById(id);
    }

    @PostMapping
    public TeamResponseDTO createTeam(@RequestBody @Valid TeamCreateDTO teamCreateDTO) {
        return teamService.saveTeam(teamCreateDTO);
    }

    @PutMapping("/{id}")
    public TeamResponseDTO updateTeam(@PathVariable("id") Long id , @RequestBody TeamCreateDTO teamCreateDTO) {
        return teamService.updateTeam(id, teamCreateDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteTeam(@PathVariable("id") Long id) {
        if(teamService.deleteTeam(id)){
            return "Team has been deleted";
        }
        return "Team has not been deleted";
    }
}
