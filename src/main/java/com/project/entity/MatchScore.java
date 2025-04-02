package com.project.entity;

import com.project.dto.PlayerDto;
import com.project.util.Player;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class MatchScore {

    private final Map<String, Integer> scores;
    @Getter
    private final String player1Name;
    @Getter
    private final String player2Name;
    @Getter
    private boolean isMatchOver = false;

    public MatchScore(PlayerDto player1, PlayerDto player2) {
        player1Name = player1.playerName();
        player2Name = player2.playerName();
        scores = new HashMap<>();
        scores.put("player1Id", player1.id());
        scores.put("player2Id", player2.id());
        scores.put("player1Points", 0);
        scores.put("player2Points", 0);
        scores.put("player1Set1Score", 0);
        scores.put("player2Set1Score", 0);
        scores.put("player1Set2Score", 0);
        scores.put("player2Set2Score", 0);
        scores.put("player1Set3Score", 0);
        scores.put("player2Set3Score", 0);
        scores.put("winnerId", 0);
    }


    public Integer getPoints(String player) {
        if (player.equals(Player.ONE.getName())) {
            return scores.get("player1Points");
        }
        return scores.get("player2Points");
    }


    public Integer getSet1Score(String player) {
        if (player.equals(Player.ONE.getName())) {
            return scores.get("player1Set1Score");
        }
        return scores.get("player2Set1Score");
    }


    public Integer getSet2Score(String player) {
        if (player.equals(Player.ONE.getName())) {
            return scores.get("player1Set2Score");
        }
        return scores.get("player2Set2Score");
    }


    public Integer getSet3Score(String player) {
        if (player.equals(Player.ONE.getName())) {
            return scores.get("player1Set3Score");
        }
        return scores.get("player2Set3Score");
    }


    public Integer getPlayer1Id() {
        return scores.get("player1Id");
    }


    public Integer getPlayer2Id() {
        return scores.get("player2Id");
    }


    public Integer getWinnerId() {
        return scores.get("winnerId");
    }


    public void setPoints(String player, Integer points) {
        if (player.equals(Player.ONE.getName())) {
            scores.put("player1Points", points);
        } else {
            scores.put("player2Points", points);
        }
    }


    public void setSet1Score(String player, Integer score) {
        if (player.equals(Player.ONE.getName())) {
            scores.put("player1Set1Score", score);
        } else {
            scores.put("player2Set1Score", score);
        }
    }


    public void setSet2Score(String player, Integer score) {
        if (player.equals(Player.ONE.getName())) {
            scores.put("player1Set2Score", score);
        } else {
            scores.put("player2Set2Score", score);
        }
    }


    public void setSet3Score(String player, Integer score) {
        if (player.equals(Player.ONE.getName())) {
            scores.put("player1Set3Score", score);
        } else {
            scores.put("player2Set3Score", score);
        }
    }


    public void setTheWinnerOfTheMatch(Integer winnerId) {
        scores.put("winnerId", winnerId);
        isMatchOver = true;
    }


    private Map<String, Integer> getMap() {
        return scores;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final MatchScore other = (MatchScore) obj;
        return this.getMap().equals(other.getMap());
    }
}