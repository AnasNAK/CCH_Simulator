package org.NAK.mapper;

import org.NAK.DTO.Team.TeamCreateDTO;
import org.NAK.DTO.Team.TeamResponseDTO;
import org.NAK.entities.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team toEntity(TeamCreateDTO teamCreateDTO);

    @Named("toCyclistCreateDTO")
    TeamCreateDTO toCyclistCreateDTO(Team team);

    @Named("toResponseDTO")
    TeamResponseDTO toResponseDTO(Team team);

    List<TeamResponseDTO> toResponseDTOList(List<Team> teams);
    List<Team> toEntityList(List<TeamCreateDTO> teamCreateDTOs);
}