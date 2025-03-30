package com.project.entity.matchState.pointsState;

import com.project.entity.matchState.MatchState;

public class TiebreakState extends PointsState {
    TiebreakState(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints(int winnerId) {

    }
}
