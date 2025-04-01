package com.project.entity.matchState;

public class SetOneState extends MatchState {

    public SetOneState(Score score) {
        super(score);
    }

    @Override
    public void changeScore() {

        pointsState.changePoints();

        if (isSetOver()) {
            score.setMatchState(new SetTwoState(score));
        }
    }
}