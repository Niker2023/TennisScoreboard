package com.project.entity.matchState;

import lombok.Getter;

public abstract class MatchState {

    @Getter
    Score score;
    @Getter
    PointsState pointsState;

    String winner;
    String loser;

    MatchState(Score score){
        this.score = score;
        pointsState = new GameState(this);
    }


    public abstract void changeScore();
}
