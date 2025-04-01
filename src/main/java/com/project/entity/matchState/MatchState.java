package com.project.entity.matchState;

import com.project.util.Set;
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


    void increaseSetScore() {
        switch (this.getClass().getSimpleName()) {
            case "SetOneState" -> {
                score.setSet1Score(winner, score.getSet1Score(winner) + 1);
            }
            case "SetTwoState" -> {
                score.setSet2Score(winner, score.getSet2Score(winner) + 1);
            }
            case "SetThreeState" -> {
                score.setSet3Score(winner, score.getSet3Score(winner) + 1);
            }
        }
    }


    boolean isSetOver() {
        return ((score.getSetScore(winner, this.getClass().getSimpleName()) > 5
                && (score.getSetScore(winner, this.getClass().getSimpleName()) - score.getSetScore(loser, this.getClass().getSimpleName()) > 1)))
                || score.getSetScore(winner, this.getClass().getSimpleName()) == 7;
    }
}
