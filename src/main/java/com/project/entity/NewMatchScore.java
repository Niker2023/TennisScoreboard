package com.project.entity;

import com.project.dto.PlayerDto;
import com.project.entity.matchState.MatchState;
import com.project.entity.matchState.SetOneState;
import lombok.Getter;
import lombok.Setter;

public class NewMatchScore {
    @Getter
    private MatchState matchState;
    @Getter
    @Setter
    private int player1Points = 0;
    @Getter
    @Setter
    private int player2Points = 0;
    @Getter
    private int player1Set1Score = 0;
    @Getter
    private int player2Set1Score = 0;
    @Getter
    private int player1Set2Score = 0;
    @Getter
    private int player2Set2Score = 0;
    @Getter
    private int player1Set3Score = 0;
    @Getter
    private int player2Set3Score = 0;
    @Getter
    private final int Player1Id;
    @Getter
    private final int Player2Id;
    @Getter
    private final String Player1Name;
    @Getter
    private final String Player2Name;


    public NewMatchScore(PlayerDto player1, PlayerDto player2) {
        matchState = new SetOneState(this);
        Player1Id = player1.id();
        Player2Id = player2.id();
        Player1Name = player1.playerName();
        Player2Name = player2.playerName();
    }


    public void playerWinsPoint(PlayerDto winner) {
        matchState.changeScore(winner.id());
    }


    public int getPoints(int playerId) {
        if (playerId == Player1Id) {
            return player1Points;
        } else {
            return player2Points;
        }
    }


    public void changeWinnerPoints(int winnerId) {
        if (winnerId == Player1Id) {
            player1Points += 1;
        } else {
            player2Points += 1;
        }
    }


    public void changeWinnerSetScore(int winnerId) {
        if (matchState instanceof SetOneState) {
            if (winnerId == Player1Id) {
                player1Set1Score += 1;
            } else {
                player2Set1Score += 1;
            }
        }

    }


    public void changeMatchState(MatchState newMatchState) {
        matchState = newMatchState;
    }


}
