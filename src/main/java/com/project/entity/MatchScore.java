package com.project.entity;

import com.project.util.Player;

import java.util.HashMap;
import java.util.Map;

public class MatchScore {

    private final Map<String, String> scores;

    public MatchScore(Integer player1, Integer player2) {
        scores = new HashMap<>();
        scores.put("player1Id", player1.id().toString());
        scores.put("player2Id", player2.id().toString());
        scores.put("player1Name", player1.playerName());
        scores.put("player2Name", player2.playerName());
        scores.put("player1Points", "0");
        scores.put("player2Points", "0");
        scores.put("player1Set1Score", "0");
        scores.put("player2Set1Score", "0");
        scores.put("player1Set2Score", "0");
        scores.put("player2Set2Score", "0");
        scores.put("player1Set3Score", "0");
        scores.put("player2Set3Score", "0");
        scores.put("winnerId", "0");
    }


    public Integer getPoints(String player) {
        if (player.equals(Player.ONE.toString())) {
            return Integer.parseInt(scores.get("player1Points"));
        }
        return Integer.parseInt(scores.get("player2Points"));
    }


    public Integer getSet1Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return Integer.parseInt(scores.get("player1Set1Score"));
        }
        return Integer.parseInt(scores.get("player2Set1Score"));
    }


    public Integer getSet2Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return Integer.parseInt(scores.get("player1Set2Score"));
        }
        return Integer.parseInt(scores.get("player2Set2Score"));
    }


    public Integer getSet3Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return Integer.parseInt(scores.get("player1Set3Score"));
        }
        return Integer.parseInt(scores.get("player2Set3Score"));
    }


    public Integer getPlayer1Id() {
        return Integer.parseInt(scores.get("player1Id"));
    }


    public Integer getPlayer2Id() {
        return Integer.parseInt(scores.get("player2Id"));
    }


    public Integer getWinnerId() {
        return Integer.parseInt(scores.get("winnerId"));
    }


    public void setPoints(String player, Integer points) {
        if (player.equals(Player.ONE.toString())) {
            scores.put("player1Points", points.toString());
        } else {
            scores.put("player2Points", points.toString());
        }
    }


    public void setSet1Score(String player, Integer score) {
        if (player.equals(Player.ONE.toString())) {
            scores.put("player1Set1Score", score.toString());
        } else {
            scores.put("player2Set1Score", score.toString());
        }
    }


    public void setSet2Score(String player, Integer score) {
        if (player.equals(Player.ONE.toString())) {
            scores.put("player1Set2Score", score.toString());
        } else {
            scores.put("player2Set2Score", score.toString());
        }
    }


    public void setSet3Score(String player, Integer score) {
        if (player.equals(Player.ONE.toString())) {
            scores.put("player1Set3Score", score.toString());
        } else {
            scores.put("player2Set3Score", score.toString());
        }
    }


    public void setTheWinnerOfTheMatch(Integer winnerId) {
        scores.put("winnerId", winnerId.toString());
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