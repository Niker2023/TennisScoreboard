package com.project.entity.matchState;

public class GameState extends PointsState {

    public GameState(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints() {

        matchState.score.setPoints(matchState.winner, matchState.score.getPoints(matchState.winner) + 1);

        if (matchState.getScore().getPoints(matchState.winner) > 3
                && (matchState.score.getPoints(matchState.winner) - matchState.score.getPoints(matchState.loser)) > 1) {
            matchState.score.setSet1Score(matchState.winner, matchState.score.getSet1Score(matchState.winner) + 1);

            if (matchState.getScore().getSetScore(matchState.winner, matchState.toString()) == 6
                    && matchState.getScore().getSetScore(matchState.loser, matchState.toString()) == 6) {
                matchState.pointsState = new TiebreakState(matchState);
            } else {
                matchState.pointsState = new GameState(matchState);
            }
        }
    }
}
