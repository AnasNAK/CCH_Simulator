package org.NAK.services.contracts;

import org.NAK.DTO.Team.TeamCreateDTO;
import org.NAK.DTO.Team.TeamResponseDTO;
import org.NAK.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    TeamResponseDTO findTeamById(Long id);
    Optional<Team> findTeamByName(String name);
    TeamResponseDTO saveTeam(TeamCreateDTO teamCreateDTO);
    List<TeamResponseDTO> findAllTeams();
    boolean deleteTeam(Long id);
    TeamResponseDTO updateTeam(Long id , TeamCreateDTO teamCreateDTO);
}
