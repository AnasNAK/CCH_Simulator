package org.NAK.services.implementations;


import jakarta.persistence.EntityNotFoundException;
import org.NAK.DTO.Team.TeamCreateDTO;
import org.NAK.DTO.Team.TeamResponseDTO;
import org.NAK.dao.contracts.TeamDAO;
import org.NAK.dao.implementations.TeamDAOImpl;
import org.NAK.entities.Team;
import org.NAK.mapper.TeamMapper;
import org.NAK.services.contracts.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamDAO teamDAO;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamServiceImpl(TeamDAOImpl teamDAO , TeamMapper teamMapper) {
        this.teamDAO = teamDAO;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamResponseDTO findTeamById(Long id) {
        return  teamMapper.toResponseDTO(teamDAO.findById(id).get());
    }

    @Override
    public Optional<Team> findTeamByName(String name) {
        return Optional.empty();
    }

    @Override
    public TeamResponseDTO saveTeam(TeamCreateDTO teamCreateDTO) {
        Team team = teamMapper.toEntity(teamCreateDTO);
        teamDAO.save(team);
        return teamMapper.toResponseDTO(team);
    }

    @Override
    public List<TeamResponseDTO> findAllTeams() {
        return teamMapper.toResponseDTOList(teamDAO.findAll());
    }

    @Override
    public boolean deleteTeam(Long id) {
        Optional<Team> existedTeam = teamDAO.findById(id);
        if(existedTeam.isEmpty()) {
            return false;
        }
        teamDAO.delete(existedTeam.get());
        return true;
    }

    @Override
    public TeamResponseDTO updateTeam(Long id , TeamCreateDTO teamCreateDTO) {
        //make entity from the dto create


        //find team in database
        Team existedTeam = teamDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Team Not found"));
        //to entity
        Team team = teamMapper.toEntity(teamCreateDTO);
        team.setId(id);

        Team updateTeam = teamDAO.update(team).orElseThrow(() -> new EntityNotFoundException("Team Not updated"));

        return teamMapper.toResponseDTO(updateTeam);
    }
}
