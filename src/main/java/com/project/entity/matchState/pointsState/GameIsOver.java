package com.project.entity.matchState.pointsState;

import com.project.entity.matchState.MatchState;

public class GameIsOver extends PointsState {
    public GameIsOver(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints(int winnerId) {

    }
}
