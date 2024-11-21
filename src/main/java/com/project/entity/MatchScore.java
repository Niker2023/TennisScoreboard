package com.project.entity;

import com.project.util.Player;

import java.util.HashMap;
import java.util.Map;

public class MatchScore {

    private Map<String, Integer> scores = new HashMap<>();

    public MatchScore(Integer player1, Integer player2) {
        scores.put("player1Id", player1);
        scores.put("player2Id", player2);
        scores.put("player1Points", 0);
        scores.put("player2Points", 0);
        scores.put("player1Set1Score", 0);
        scores.put("player2Set1Score", 0);
        scores.put("player1Set2Score", 0);
        scores.put("player2Set2Score", 0);
        scores.put("player1Set3Score", 0);
        scores.put("player2Set3Score", 0);
    }


    public Integer getPoints(String player) {
        if (player.equals(Player.ONE.toString())) {
            return scores.get("player1Points");
        }
        return scores.get("player2Points");
    }


    public Integer getSet1Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return scores.get("player1Set1Score");
        }
        return scores.get("player2Set1Score");
    }


    public Integer getSet2Score(String player) {
        if (player.equals(Player.ONE.toString())) {
            return scores.get("player1Set2Score");
        }
        return scores.get("player2Set2Score");
    }


    public Integer getSet3Score(String player) {
        if (player.equals(Player.ONE.toString())) {
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


    public void setPoints(String player, Integer points) {
        if (player.equals(Player.ONE.toString())) {
            scores.put("player1Points", points);
        } else {
            scores.put("player2Points", points);
        }
    }


    public void setSet1Score(String player, Integer score) {
        if (player.equals(Player.ONE.toString())) {
            scores.put("player1Set1Score", score);
        } else {
            scores.put("player2Set1Score", score);
        }
    }
}
