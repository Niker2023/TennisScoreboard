package com.project.entity.matchState;

public class TiebreakState extends PointsState {
    TiebreakState(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints() {

        increasePoints();

        if (isTiebreakOver()) {
            matchState.increaseSetScore();

            matchState.pointsState = new GameState(matchState);
        }
    }


    private boolean isTiebreakOver() {
        return matchState.getScore().getPoints(matchState.winner) > 5
                && (matchState.score.getPoints(matchState.winner) - matchState.score.getPoints(matchState.loser)) > 1;
    }
}
