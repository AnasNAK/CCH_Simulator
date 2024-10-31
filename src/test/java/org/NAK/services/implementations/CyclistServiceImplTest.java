package org.NAK.services.implementations;

import org.NAK.DTO.Cyclist.CyclistCreateDTO;
import org.NAK.DTO.Cyclist.CyclistResponseDTO;
import org.NAK.dao.contracts.CyclistDAO;
import org.NAK.dao.contracts.TeamDAO;
import org.NAK.entities.Cyclist;
import org.NAK.entities.Team;
import org.NAK.mapper.Cyclistmapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CyclistServiceImplTest {

    @Mock
    private CyclistDAO cyclistDAO;

    @Mock
    private Cyclistmapper cyclistmapper;

    @Mock
    private TeamDAO teamDAO;

    @InjectMocks
    private CyclistServiceImpl cyclistServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCyclist_WithValidCyclistCreateDTO_ReturnsCyclistResponseDTO() {
        CyclistCreateDTO cyclistCreateDTO = new CyclistCreateDTO();
        cyclistCreateDTO.setTeamId(1L);

        Cyclist cyclist = new Cyclist();
        Team team = new Team();
        CyclistResponseDTO responseDTO = new CyclistResponseDTO();

        team.setId(1L);
        cyclist.setId(1L);
        cyclist.setTeam(team);

        when(cyclistmapper.toEntity(cyclistCreateDTO)).thenReturn(cyclist);
        when(cyclistDAO.save(cyclist)).thenReturn(Optional.of(cyclist));
        when(cyclistmapper.toResponseDto(cyclist)).thenReturn(responseDTO);
        when(teamDAO.findById(cyclistCreateDTO.getTeamId())).thenReturn(Optional.of(team));

        CyclistResponseDTO result = cyclistServiceImpl.saveCyclist(cyclistCreateDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(cyclistDAO).save(cyclist);
        verify(teamDAO).findById(cyclistCreateDTO.getTeamId());
    }

    @Test
    void saveCyclist_WithInvalidTeamId_ThrowsEntityNotFoundException() {
        CyclistCreateDTO cyclistCreateDTO = new CyclistCreateDTO();
        cyclistCreateDTO.setTeamId(1L);

        Cyclist cyclist = new Cyclist();
        Team team = new Team();
        team.setId(1L);
        cyclist.setTeam(team);

        when(cyclistmapper.toEntity(cyclistCreateDTO)).thenReturn(cyclist);

        when(teamDAO.findById(cyclist.getTeam().getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            cyclistServiceImpl.saveCyclist(cyclistCreateDTO);
        });

        assertEquals("Team Not Found", exception.getMessage());

        verify(cyclistDAO, never()).save(any());
    }






    @Test
    void updateCyclist_WithExistingId_ReturnsUpdatedCyclistResponseDTO() {
        Long cyclistId = 1L;
        CyclistCreateDTO cyclistCreateDTO = new CyclistCreateDTO();
        Cyclist existingCyclist = new Cyclist();
        Cyclist updatingCyclist = new Cyclist();
        CyclistResponseDTO responseDTO = new CyclistResponseDTO();

        existingCyclist.setId(cyclistId);
        existingCyclist.setTeam(new Team());

        when(cyclistDAO.findById(cyclistId)).thenReturn(Optional.of(existingCyclist));
        when(cyclistmapper.toEntity(cyclistCreateDTO)).thenReturn(updatingCyclist);

        when(cyclistDAO.update(updatingCyclist)).thenReturn(Optional.of(updatingCyclist));
        when(cyclistmapper.toResponseDto(updatingCyclist)).thenReturn(responseDTO);

        Optional<CyclistResponseDTO> result = cyclistServiceImpl.updateCyclist(cyclistId, cyclistCreateDTO);

        assertTrue(result.isPresent());
        assertEquals(responseDTO, result.get());
        verify(cyclistDAO).update(updatingCyclist);
    }



    @Test
    void findAllCyclists_ReturnsEmptyList() {
        when(cyclistDAO.findAll()).thenReturn(Collections.emptyList());
        when(cyclistmapper.toResponseDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<CyclistResponseDTO> list = cyclistServiceImpl.findAllCyclists();

        assertTrue(list.isEmpty());
        verify(cyclistDAO).findAll();
    }

    @Test
    void deleteCyclist_WithExistingId_ReturnsTrue() {
        Long cyclistId = 1L;
        Cyclist cyclist = new Cyclist();

        when(cyclistDAO.findById(cyclistId)).thenReturn(Optional.of(cyclist));

        boolean result = cyclistServiceImpl.deleteCyclist(cyclistId);

        assertTrue(result);
        verify(cyclistDAO).delete(cyclist);


    }

    @Test
    void findCyclistById_WithExistingId_ReturnsCyclistResponseDTO() {
        Long cyclistId = 1L;
        Cyclist cyclist = new Cyclist();
        CyclistResponseDTO responseDTO = new CyclistResponseDTO();

        when(cyclistDAO.findById(cyclistId)).thenReturn(Optional.of(cyclist));
        when(cyclistmapper.toResponseDto(cyclist)).thenReturn(responseDTO);

        Optional<CyclistResponseDTO> result = cyclistServiceImpl.findCyclistById(cyclistId);

        assertTrue(result.isPresent());
        verify(cyclistDAO).findById(cyclistId);
    }

    @Test
    void findCyclistById_WithInvalidId_ReturnsEmptyOptional() {
        Long cyclistId = 1L;

        when(cyclistDAO.findById(cyclistId)).thenReturn(Optional.empty());

        Optional<CyclistResponseDTO> result = cyclistServiceImpl.findCyclistById(cyclistId);

        assertTrue(result.isEmpty(), "Expected an empty Optional when cyclist not found");

        verify(cyclistDAO).findById(cyclistId);
    }





}