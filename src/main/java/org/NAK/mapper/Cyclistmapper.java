package org.NAK.mapper;

import org.NAK.DTO.Cyclist.CyclistCreateDTO;
import org.NAK.DTO.Cyclist.CyclistResponseDTO;
import org.NAK.entities.Cyclist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring" , uses = TeamMapper.class)
public interface Cyclistmapper {

    @Named("toResponseDTO")
    @Mapping(source = "team" , target = "team" , qualifiedByName = "toCyclistCreateDTO")
    CyclistResponseDTO toResponseDto(Cyclist cyclist);


    @Mapping(source = "teamId" , target = "team.id")
    Cyclist toEntity(CyclistCreateDTO cyclistCreateDTO);


    CyclistCreateDTO toCyclistCreateDTO(Cyclist cyclist);
    List<CyclistResponseDTO> toResponseDtoList(List<Cyclist> cyclistList);
    List<Cyclist> toEntityList(List<CyclistCreateDTO> cyclistCreateDTOList);
}
