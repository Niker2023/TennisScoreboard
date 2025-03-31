package com.project.entity.matchState.pointsState;

import com.project.entity.matchState.MatchState;

public class GameState extends PointsState {

    private final int LOSER_ID = 0;

    public GameState(MatchState matchState) {
        super(matchState);
    }

    @Override
    public void changePoints(int winnerId) {

        matchState.getNewMatchScore().changeWinnerPoints(winnerId);

        int winnerPoints = matchState.getNewMatchScore().getPoints(winnerId);
        int loserPoints = matchState.getNewMatchScore().getPoints(LOSER_ID);

        if (winnerPoints > 2 && (winnerPoints - loserPoints) > 1) {
            matchState.changePointsState(new GameIsOver(matchState));
        }
    }
}
