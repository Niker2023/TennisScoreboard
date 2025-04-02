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


    @Override
    String getPointsView(String playerOrder) {
        return matchState.score.getPoints(playerOrder).toString();
    }


    private boolean isTiebreakOver() {
        int MIN_POINTS_TO_WIN_TIEBREAK = 7;
        return matchState.getScore().getPoints(matchState.winner) >= MIN_POINTS_TO_WIN_TIEBREAK
                && (matchState.score.getPoints(matchState.winner) - matchState.score.getPoints(matchState.loser)) >= matchState.MIN_POINTS_DIFF_TO_WIN;
    }
}
