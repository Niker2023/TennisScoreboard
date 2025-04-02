package com.project.entity.matchState;

import com.project.util.Player;

public abstract class PointsState {

    MatchState matchState;

    public PointsState(MatchState matchState) {
        this.matchState = matchState;
        matchState.score.setPoints(Player.ONE.getName(),0);
        matchState.score.setPoints(Player.TWO.getName(),0);
    }


    public abstract void changePoints();


    void increasePoints() {
        matchState.score.setPoints(matchState.winner, matchState.score.getPoints(matchState.winner) + 1);
    }


    abstract String getPointsView(String playerOrder);
}
