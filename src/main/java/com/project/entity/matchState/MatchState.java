package com.project.entity.matchState;

import com.project.entity.NewMatchScore;
import com.project.entity.matchState.pointsState.GameState;
import com.project.entity.matchState.pointsState.PointsState;
import lombok.Getter;

public abstract class MatchState {

    @Getter
    NewMatchScore newMatchScore;
    PointsState pointsState;


    MatchState(NewMatchScore newMatchScore){
        this.newMatchScore = newMatchScore;
        pointsState = new GameState(this);
    }


    public abstract void changeScore(int winnerId);


    public void changePointsState(PointsState pointsState) {
        this.pointsState = pointsState;
    }

}
