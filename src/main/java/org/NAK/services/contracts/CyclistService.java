package org.NAK.services.contracts;

import org.NAK.DTO.Cyclist.CyclistCreateDTO;
import org.NAK.DTO.Cyclist.CyclistResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CyclistService {
    CyclistResponseDTO saveCyclist(CyclistCreateDTO cyclistCreateDTO);
    Optional<CyclistResponseDTO> updateCyclist(Long id , CyclistCreateDTO cyclistCreateDTO);
    List<CyclistResponseDTO> findAllCyclists();
    boolean deleteCyclist(Long id);
    Optional<CyclistResponseDTO> findCyclistById(Long id);
}
