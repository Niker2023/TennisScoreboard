package com.project.entity.matchState;

import com.project.dto.PlayerDto;
import com.project.util.Player;
import com.project.util.SetNumber;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@Setter(AccessLevel.PACKAGE)
public class Score {

    @EqualsAndHashCode.Exclude
    private MatchState matchState;

    private int player1Points = 0;
    private int player2Points = 0;
    private int player1Set1Score = 0;
    private int player2Set1Score = 0;
    private int player1Set2Score = 0;
    private int player2Set2Score = 0;
    private int player1Set3Score = 0;
    private int player2Set3Score = 0;
    private final int Player1Id;
    private final int Player2Id;
    private final String Player1Name;
    private final String Player2Name;
    private int winnerId;


    public Score(PlayerDto player1, PlayerDto player2) {
        matchState = new SetOneState(this);
        Player1Id = player1.id();
        Player2Id = player2.id();
        Player1Name = player1.playerName();
        Player2Name = player2.playerName();
    }


    public void SetStatesForTests(int setSetNumber, boolean setTiebreak) {
        switch (setSetNumber) {
            case 1 -> {
                matchState = new SetOneState(this);
            }
            case 2 -> {
                matchState = new SetTwoState(this);
            }
            case 3 -> {
                matchState = new SetThreeState(this);
            }
        }
        if (setTiebreak) {
            matchState.pointsState = new TiebreakState(matchState);
        }
    }


    public String getPointsForDisplay(String playerOrder) {
        return matchState.pointsState.getPointsView(playerOrder);
    }


    public String getWinnerName() {
        if (winnerId == Player1Id) {
            return Player1Name;
        } else {
            return Player2Name;
        }
    }


    public void playerWinsPointById(int winnerId) {
        if (winnerId == getPlayer1Id()) {
            matchState.winner = Player.ONE.getName();
            matchState.loser = Player.TWO.getName();
        } else if (winnerId == getPlayer2Id()) {
            matchState.winner = Player.TWO.getName();
            matchState.loser = Player.ONE.getName();
        }
        matchState.changeScore();
    }


    public void playerWinsPointByPlayerOrder(String playerOrder) {
        if (playerOrder.equals(Player.ONE.getName())) {
            matchState.winner = Player.ONE.getName();
            matchState.loser = Player.TWO.getName();
        } else if (playerOrder.equals(Player.TWO.getName())) {
            matchState.winner = Player.TWO.getName();
            matchState.loser = Player.ONE.getName();
        }
        matchState.changeScore();
    }


    public Integer getPoints(String player) {
        if (player.equals(Player.ONE.getName())) {
            return player1Points;
        }
        return player2Points;
    }


    public Integer getSet1Score(String player) {
        if (player.equals(Player.ONE.getName())) {
            return player1Set1Score;
        }
        return player2Set1Score;
    }


    public Integer getSetScore(String player, String set) {
        if (player.equals(Player.ONE.getName())) {
            if (set.equals(SetNumber.ONE.getName())) {
                return player1Set1Score;
            } else if (set.equals(SetNumber.TWO.getName())) {
                return player1Set2Score;
            } else {
                return player1Set3Score;
            }
        } else {
            if (set.equals(SetNumber.ONE.getName())) {
                return player2Set1Score;
            } else if (set.equals(SetNumber.TWO.getName())) {
                return player2Set2Score;
            } else {
                return player2Set3Score;
            }
        }
    }


    public Integer getSet2Score(String player) {
        if (player.equals(Player.ONE.getName())) {
            return player1Set2Score;
        }
        return player2Set2Score;
    }


    public Integer getSet3Score(String player) {
        if (player.equals(Player.ONE.getName())) {
            return player1Set3Score;
        }
        return player2Set3Score;
    }


    public void setPoints(String player, int points) {
        if (player.equals(Player.ONE.getName())) {
            player1Points = points;
        } else {
            player2Points = points;
        }
    }


    public void setSet1Score(String player, Integer score) {
        if (player.equals(Player.ONE.getName())) {
            player1Set1Score = score;
        } else {
            player2Set1Score = score;
        }
    }


    public void setSet2Score(String player, Integer score) {
        if (player.equals(Player.ONE.getName())) {
            player1Set2Score = score;
        } else {
            player2Set2Score = score;
        }
    }


    public void setSet3Score(String player, Integer score) {
        if (player.equals(Player.ONE.getName())) {
            player1Set3Score = score;
        } else {
            player2Set3Score = score;
        }
    }


    public void setWinnerId(String playerOrder) {
        if (playerOrder.equals(Player.ONE.getName())) {
            winnerId = Player1Id;
        } else {
            winnerId = Player2Id;
        }
    }


    public boolean isMatchOver() {
        return winnerId != 0;
    }
}
