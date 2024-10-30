package org.NAK.services.implementations;


import jakarta.persistence.EntityNotFoundException;
import org.NAK.DTO.Cyclist.CyclistCreateDTO;
import org.NAK.DTO.Cyclist.CyclistResponseDTO;
import org.NAK.dao.contracts.CyclistDAO;
import org.NAK.dao.contracts.TeamDAO;
import org.NAK.entities.Cyclist;
import org.NAK.entities.Team;
import org.NAK.mapper.Cyclistmapper;
import org.NAK.services.contracts.CyclistService;
import org.NAK.services.contracts.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CyclistServiceImpl implements CyclistService {

    private final CyclistDAO cyclistDAO;
    private final Cyclistmapper cyclistmapper;
    private final TeamDAO teamDAO;

    @Autowired
    private TeamService teamService;


    @Autowired
    public CyclistServiceImpl(CyclistDAO cyclistDAO , Cyclistmapper cyclistmapper , TeamDAO teamDAO) {
        this.cyclistDAO = cyclistDAO;
        this.cyclistmapper = cyclistmapper;
        this.teamDAO = teamDAO;
    }

    @Override
    public CyclistResponseDTO saveCyclist(CyclistCreateDTO cyclistCreateDTO) {
        // change it from DTO to Entity
        Cyclist cyclist = cyclistmapper.toEntity(cyclistCreateDTO);
        //get team
        Team team = teamDAO.findById(cyclist.getTeam().getId()).orElseThrow(() -> new EntityNotFoundException("Team Not Found"));
        //set the team
        cyclist.setTeam(team);
        //save it
        Cyclist savedCyclist = cyclistDAO.save(cyclist).orElseThrow(() -> new EntityNotFoundException("Error saving the Cyclist"));
        return cyclistmapper.toResponseDto(savedCyclist);
    }

    @Override
    public Optional<CyclistResponseDTO> updateCyclist(Long id , CyclistCreateDTO cyclistCreateDTO) {

        //check if the cyclist exist
        Cyclist existednCyclist = cyclistDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Cyclist Not Found"));
        //change Updated Cyclist to ENtity
        Cyclist updatedCyclist = cyclistmapper.toEntity(cyclistCreateDTO);
        updatedCyclist.setId(id);

        //update team if necessary
        if(cyclistCreateDTO.getTeamId() != null){
            Team team = teamDAO.findById(cyclistCreateDTO.getTeamId()).orElseThrow(() -> new EntityNotFoundException("Team Not Found"));
            updatedCyclist.setTeam(team);
        }else {
            updatedCyclist.setTeam(existednCyclist.getTeam());
        }

        //update the cyclist
        Cyclist savedCyclist = cyclistDAO.update(updatedCyclist).orElseThrow(() -> new RuntimeException("Failed to update the Cyclist"));
        //return the team
        return Optional.of(cyclistmapper.toResponseDto(savedCyclist));
    }

    @Override
    public List<CyclistResponseDTO> findAllCyclists() {
        return cyclistmapper.toResponseDtoList(cyclistDAO.findAll());
    }

    @Override
    public boolean deleteCyclist(Long id) {
        Optional<Cyclist> cyclistOptional = cyclistDAO.findById(id);
//        if(!cyclistOptional.isPresent()) {
//            throw  new EntityNotFoundException("Cyclist not found");
//        }
        cyclistDAO.delete(cyclistOptional.get());
        return true;
    }

    @Override
    public Optional<CyclistResponseDTO> findCyclistById(Long id) {
        Optional<Cyclist> cyclist = cyclistDAO.findById(id);
        return Optional.of(cyclistmapper.toResponseDto(cyclist.get()));
    }
}
