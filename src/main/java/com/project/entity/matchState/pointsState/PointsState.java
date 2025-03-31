package com.project.entity.matchState.pointsState;

import com.project.dto.PlayerDto;
import com.project.entity.NewMatchScore;
import com.project.entity.matchState.MatchState;

public abstract class PointsState {

    MatchState matchState;

    public PointsState(MatchState matchState) {
        this.matchState = matchState;
    }

    public abstract void changePoints(int winnerId);
}
