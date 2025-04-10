package com.project.entity.match_state;

public class SetTwoState extends MatchState {

    SetTwoState(Score score) {
        super(score);
    }

    @Override
    public void changeScore() {

        pointsState.changePoints();

        if (isSetOver()) {
            if (isMatchOver()) {
                score.setWinnerId(winner);
            } else {
                score.setMatchState(new SetThreeState(score));
            }
        }
    }


    private boolean isMatchOver() {
        return score.getSet1Score(winner) > MIN_SCORE_TO_WIN
                || (score.getSet1Score(winner) == MIN_SCORE_TO_WIN
                && (score.getSet1Score(winner) - score.getSet1Score(loser) >= MIN_POINTS_DIFF_TO_WIN));
    }
}
