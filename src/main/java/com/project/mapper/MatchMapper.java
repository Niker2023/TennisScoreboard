package com.project.mapper;

import com.project.dto.FinishedMatchDto;
import com.project.entity.Matches;
import com.project.entity.Players;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(source = "match.player1", target = "player1", qualifiedByName = "getNameFromPlayer")
    @Mapping(source = "match.player2", target = "player2", qualifiedByName = "getNameFromPlayer")
    @Mapping(source = "match.winner", target = "winner", qualifiedByName = "getNameFromPlayer")
    FinishedMatchDto DtoFromMatch(Matches match);

    @Named("getNameFromPlayer")
    default String getPlayerName(Players player) {
        return player.getName();
    }
}
