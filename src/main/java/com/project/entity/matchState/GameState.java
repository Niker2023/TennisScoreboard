package com.project.entity.matchState;

import com.project.util.Player;

public class GameState extends PointsState {

    public GameState(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints() {

        increasePoints();

        if (isGameOver()) {
            matchState.increaseSetScore();

            matchState.pointsState = isNextTiebreak()
                    ? new TiebreakState(matchState)
                    : new GameState(matchState);
        }
    }

    @Override
    String getPointsView(String playerOrder) {

        String player1;
        String player2;

        if (Player.ONE.getName().equals(playerOrder)) {
            player1 = Player.ONE.getName();
            player2 = Player.TWO.getName();
        } else {
            player2 = Player.ONE.getName();
            player1 = Player.TWO.getName();
        }

        int MIN_POINTS_FOR_ADVANTAGE = 3;
        int POINTS_DIFF_FOR_ADVANTAGE = 1;

        if (matchState.score.getPoints(player1) >= MIN_POINTS_FOR_ADVANTAGE
                && matchState.score.getPoints(player2) >= MIN_POINTS_FOR_ADVANTAGE) {
            if (matchState.score.getPoints(player2) - matchState.score.getPoints(player1)
                    == POINTS_DIFF_FOR_ADVANTAGE) {
                return "<br>";
            } else if (matchState.score.getPoints(player1) - matchState.score.getPoints(player2)
                    == POINTS_DIFF_FOR_ADVANTAGE) {
                return "AD";
            }
            return "40";
        } else if (matchState.score.getPoints(player1) == 1) {
            return "15";
        } else if (matchState.score.getPoints(player1) == 2) {
            return "30";
        } else if (matchState.score.getPoints(player1) == 3) {
            return "40";
        }
        return "0";
    }


    private boolean isNextTiebreak() {
        return matchState.getScore().getSetScore(matchState.winner, matchState.getClass().getSimpleName()) == matchState.MIN_SCORE_TO_WIN
                && matchState.getScore().getSetScore(matchState.loser, matchState.getClass().getSimpleName()) == matchState.MIN_SCORE_TO_WIN;
     }


     private boolean isGameOver() {
        int MIN_POINTS_TO_WIN_GAME = 4;
        return matchState.getScore().getPoints(matchState.winner) >= MIN_POINTS_TO_WIN_GAME
                && (matchState.score.getPoints(matchState.winner) - matchState.score.getPoints(matchState.loser)) >= matchState.MIN_POINTS_DIFF_TO_WIN;
     }
}
