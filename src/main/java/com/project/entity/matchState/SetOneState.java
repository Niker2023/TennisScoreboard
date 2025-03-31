package com.project.entity.matchState;

import com.project.entity.NewMatchScore;
import com.project.entity.matchState.pointsState.GameIsOver;

public class SetOneState extends MatchState {

    public SetOneState(NewMatchScore newMatchScore) {
        super(newMatchScore);
    }

    @Override
    public void changeScore(int winnerId) {

        pointsState.changePoints(winnerId);
        if (pointsState instanceof GameIsOver) {
            newMatchScore.
        }
    }


}
