package com.project.entity.matchState;

import com.project.util.SetNumber;
import lombok.Getter;

public abstract class MatchState {

    @Getter
    Score score;
    @Getter
    PointsState pointsState;

    final int MIN_POINTS_DIFF_TO_WIN = 2;
    final int MIN_SCORE_DIFF_TO_WIN = 2;
    final int MIN_SCORE_TO_WIN = 6;

    String winner;
    String loser;

    MatchState(Score score){
        this.score = score;
        pointsState = new GameState(this);
    }


    public abstract void changeScore();


    void increaseSetScore() {
        String className = this.getClass().getSimpleName();
        SetNumber setNumber = SetNumber.getSetNumberByName(className);
        switch (setNumber) {
            case ONE -> {
                score.setSet1Score(winner, score.getSet1Score(winner) + 1);
            }
            case TWO -> {
                score.setSet2Score(winner, score.getSet2Score(winner) + 1);
            }
            case THREE -> {
                score.setSet3Score(winner, score.getSet3Score(winner) + 1);
            }
        }
    }


    boolean isSetOver() {
        String currentSet = this.getClass().getSimpleName();
        return ((score.getSetScore(winner, currentSet) == MIN_SCORE_TO_WIN
                && (score.getSetScore(winner, currentSet) - score.getSetScore(loser, currentSet) >= MIN_SCORE_DIFF_TO_WIN)))
                || score.getSetScore(winner, currentSet) > MIN_SCORE_TO_WIN;
    }
}
