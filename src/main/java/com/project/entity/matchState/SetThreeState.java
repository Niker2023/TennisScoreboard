package com.project.entity.matchState;

public class SetThreeState extends MatchState {

    SetThreeState(Score score) {
        super(score);
    }

    @Override
    public void changeScore() {

        pointsState.changePoints();

        if (isSetOver()) {
            score.setWinnerId(winner);
        }
    }
}
