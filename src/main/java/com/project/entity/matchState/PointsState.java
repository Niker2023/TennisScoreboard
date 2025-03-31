package com.project.entity.matchState;

import com.project.util.Player;

public abstract class PointsState {

    MatchState matchState;

    public PointsState(MatchState matchState) {
        this.matchState = matchState;
        matchState.score.setPoints(Player.ONE.toString(),0);
        matchState.score.setPoints(Player.TWO.toString(),0);
    }

    public abstract void changePoints();
}
