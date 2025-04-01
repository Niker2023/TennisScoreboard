package com.project.entity.matchState;

public class SetTwoState extends MatchState {

    SetTwoState(Score score) {
        super(score);
    }

    @Override
    public void changeScore() {

        pointsState.changePoints();

        if (isSetOver()) {
            if (isMatchOver()) {
                score.setMatchOver(true);
                score.setWinnerId(winner);
            } else {
                score.setMatchState(new SetThreeState(score));
            }
        }
    }


    private boolean isMatchOver() {
        return score.getSet1Score(winner) == 7
                || (score.getSet1Score(winner) == 6 && (score.getSet1Score(winner) - score.getSet1Score(loser) > 1));
    }
}
