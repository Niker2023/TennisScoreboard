package com.project.entity.matchState;

import com.project.dto.PlayerDto;
import com.project.util.Player;
import com.project.util.Set;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PACKAGE)
public class Score {

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
    private boolean isOver = false;
    private int winnerId;


    public Score(PlayerDto player1, PlayerDto player2) {
        matchState = new SetOneState(this);
        Player1Id = player1.id();
        Player2Id = player2.id();
        Player1Name = player1.playerName();
        Player2Name = player2.playerName();
    }


    public void playerWinsPoint(PlayerDto winner) {
        if (winner.id() == Player1Id) {
            matchState.winner = Player.ONE.toString();
            matchState.loser = Player.TWO.toString();
        } else if (winner.id() == Player2Id) {
            matchState.winner = Player.TWO.toString();
            matchState.loser = Player.ONE.toString();
        }
        matchState.changeScore();
    }


    public Integer getPoints(String player) {
        if (player.equals(Player.ONE.toString())) {
            return player1Points;
        }
        return player2Points;
    }


    public Integer getSet1Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return player1Set1Score;
        }
        return player2Set1Score;
    }


    public Integer getSetScore(String player, String set) {
        if (player.equals(Player.ONE.toString())) {
            if (set.equals(Set.ONE.toString())) {
                return player1Set1Score;
            } else if (set.equals(Set.TWO.toString())) {
                return player1Set2Score;
            } else {
                return player1Set3Score;
            }
        } else {
            if (set.equals(Set.ONE.toString())) {
                return player2Set1Score;
            } else if (set.equals(Set.TWO.toString())) {
                return player2Set2Score;
            } else {
                return player2Set3Score;
            }
        }
    }


    public Integer getSet2Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return player1Set2Score;
        }
        return player2Set2Score;
    }


    public Integer getSet3Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return player1Set3Score;
        }
        return player2Set3Score;
    }


    public void setPoints(String player, Integer points) {
        if (player.equals(Player.ONE.toString())) {
            player1Points = points;
        } else {
            player2Points = points;
        }
    }


    public void setSet1Score(String player, Integer score) {
        if (player.equals(Player.ONE.toString())) {
            player1Set1Score = score;
        } else {
            player2Set1Score = score;
        }
    }


    public void setSet2Score(String player, Integer score) {
        if (player.equals(Player.ONE.toString())) {
            player1Set2Score = score;
        } else {
            player2Set2Score = score;
        }
    }


    public void setSet3Score(String player, Integer score) {
        if (player.equals(Player.ONE.toString())) {
            player1Set3Score = score;
        } else {
            player2Set3Score = score;
        }
    }
}
