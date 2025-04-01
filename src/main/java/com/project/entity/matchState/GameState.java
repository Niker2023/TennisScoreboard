package com.project.entity.matchState;

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


     private boolean isNextTiebreak() {
        return matchState.getScore().getSetScore(matchState.winner, matchState.getClass().getSimpleName()) == 6
                && matchState.getScore().getSetScore(matchState.loser, matchState.getClass().getSimpleName()) == 6;
     }


     private boolean isGameOver() {
        return matchState.getScore().getPoints(matchState.winner) > 3
                && (matchState.score.getPoints(matchState.winner) - matchState.score.getPoints(matchState.loser)) > 1;
     }
}
