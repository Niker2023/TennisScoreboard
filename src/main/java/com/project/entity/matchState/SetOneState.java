package com.project.entity.matchState;

public class SetOneState extends MatchState {

    public SetOneState(Score score) {
        super(score);
    }

    @Override
    public String toString() {
        return "set1";
    }

    @Override
    public void changeScore() {
        score.setPoints(winner, score.getPoints(winner) + 1);

        if (score.getPoints(winner) > 3
                && (score.getPoints(winner) - score.getPoints(loser)) > 1) {

            score.setSet1Score(winner, score.getSet1Score(winner) + 1);

            if (score.getSetScore(winner, score.getMatchState().toString()) == 6
                    && score.getSetScore(loser, score.getMatchState().toString()) == 6) {
                if (score.getPoints(winner) > 6
                        && (score.getPoints(winner) - score.getPoints(loser)) > 1) {
                    score.setMatchState(new SetTwoState(score));
                }
            } else if (score.getSetScore(winner, score.getMatchState().toString()) > 5
                    && (score.getSetScore(winner, score.getMatchState().toString())
                    - score.getSetScore(loser, score.getMatchState().toString()) ) > 1) {
                score.setMatchState(new SetTwoState(score));
            }
        }
    }
}
