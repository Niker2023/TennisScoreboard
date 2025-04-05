package com.project.entity.match_state;

import com.project.dto.PlayerDto;
import com.project.util.Player;
import com.project.util.SetNumber;
import lombok.*;

@Data
@Setter(AccessLevel.PACKAGE)
public class Score {

    @EqualsAndHashCode.Exclude
    private MatchState matchState;

    private int INIT_VALUE = 0;

    private int player1Points = INIT_VALUE;
    private int player2Points = INIT_VALUE;
    private int player1Set1Score = INIT_VALUE;
    private int player2Set1Score = INIT_VALUE;
    private int player1Set2Score = INIT_VALUE;
    private int player2Set2Score = INIT_VALUE;
    private int player1Set3Score = INIT_VALUE;
    private int player2Set3Score = INIT_VALUE;
    private final int player1Id;
    private final int player2Id;
    private final String player1Name;
    private final String player2Name;
    private int winnerId = INIT_VALUE;

    public Score(PlayerDto player1, PlayerDto player2) {
        matchState = new SetOneState(this);
        player1Id = player1.id();
        player2Id = player2.id();
        player1Name = player1.playerName();
        player2Name = player2.playerName();
    }


    public void SetStatesForTests(int setSetNumber, boolean setTiebreak) {
        switch (setSetNumber) {
            case 1 -> matchState = new SetOneState(this);
            case 2 -> matchState = new SetTwoState(this);
            case 3 -> matchState = new SetThreeState(this);
        }
        if (setTiebreak) {
            matchState.pointsState = new TiebreakState(matchState);
        }
    }


    public String getPointsForDisplay(String playerOrder) {
        return matchState.pointsState.getPointsView(playerOrder);
    }


    public String getWinnerName() {
        if (!isMatchOver()) {
            return "Победителя еще нет.";
        }
        if (winnerId == player1Id) {
            return player1Name;
        } else {
            return player2Name;
        }
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


    Integer getPoints(String player) {
        if (player.equals(Player.ONE.getName())) {
            return player1Points;
        }
        return player2Points;
    }


    Integer getSet1Score(String player) {
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


    Integer getSet2Score(String player) {
        if (player.equals(Player.ONE.getName())) {
            return player1Set2Score;
        }
        return player2Set2Score;
    }


    Integer getSet3Score(String player) {
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
            winnerId = player1Id;
        } else {
            winnerId = player2Id;
        }
    }


    public boolean isMatchOver() {
        return winnerId != INIT_VALUE;
    }
}
