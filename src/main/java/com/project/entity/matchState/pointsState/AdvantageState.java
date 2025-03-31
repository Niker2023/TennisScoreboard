package com.project.entity.matchState.pointsState;

import com.project.dto.PlayerDto;
import com.project.entity.matchState.MatchState;

public class AdvantageState extends PointsState {
    AdvantageState(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints(int winnerId) {

    }
}
