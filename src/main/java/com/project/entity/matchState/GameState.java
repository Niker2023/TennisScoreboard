package com.project.entity.matchState;

public class GameState extends PointsState {

    public GameState(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints() {

        matchState.score.setPoints(winner, matchState.score.getPoints(winner) + 1);

        if (matchState.getScore().getPoints(winner) > 3
                && (matchState.score.getPoints(winner) - matchState.score.getPoints(matchState.loser)) > 1) {
            matchState.score.setSet1Score(winner, matchState.score.getSet1Score(winner) + 1);

            if (matchState.getScore().getSetScore(winner, matchState.toString()) == 6
                    && matchState.getScore().getSetScore(matchState.loser, matchState.toString()) == 6) {
                matchState.pointsState = new TiebreakState(matchState);
            } else {
                matchState.pointsState = new GameState(matchState);
            }
        }
    }
}
