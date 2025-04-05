package com.project.entity.match_state;

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
