package org.NAK.controller;

import org.NAK.DTO.Cyclist.CyclistCreateDTO;
import org.NAK.DTO.Cyclist.CyclistResponseDTO;
import org.NAK.services.contracts.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cyclists")
public class CyclistController {

    @Autowired
    private CyclistService cyclistService;

    @GetMapping
    public ResponseEntity<List<CyclistResponseDTO>> getCyclists() {
        List<CyclistResponseDTO> list = cyclistService.findAllCyclists();
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclistResponseDTO> getCyclist(@PathVariable("id") Long id) {
        Optional<CyclistResponseDTO> responseDTO = cyclistService.findCyclistById(id);
        if (!responseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(responseDTO.get());
    }

    @PostMapping
    public ResponseEntity<CyclistResponseDTO> createCyclist(@RequestBody CyclistCreateDTO cyclistCreateDTO) {
        CyclistResponseDTO createdCyclist = cyclistService.saveCyclist(cyclistCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCyclist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CyclistResponseDTO> updateCyclist(@PathVariable("id") Long id, @RequestBody CyclistCreateDTO cyclistCreateDTO) {
        Optional<CyclistResponseDTO> cyclistResponseDTO = cyclistService.updateCyclist(id, cyclistCreateDTO);
        if (!cyclistResponseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(cyclistResponseDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCyclist(@PathVariable("id") Long id) {
        if (!cyclistService.deleteCyclist(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This is not a valid cyclist");
        }
        return ResponseEntity.ok("The cyclist has been deleted"); 
    }
}
