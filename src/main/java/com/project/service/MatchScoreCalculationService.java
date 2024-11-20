package com.project.service;

import com.project.entity.MatchScore;

import java.util.HashMap;
import java.util.Map;

public class MatchScoreCalculationService {

    public static MatchScore calculate(MatchScore matchScore, String winner) {

        Integer player1Id = matchScore.getPlayer1Id();
        Integer player2Id = matchScore.getPlayer2Id();
        Integer winnerId;
        Integer looserId;

        if (winner.equals("player1")) {
            winnerId = player1Id;
            looserId = player2Id;
        } else {
            winnerId = player2Id;
            looserId = player1Id;
        }

        if (matchScore.getPoints().get(winnerId) < 3) {
            matchScore.setPoints(changePoints(matchScore.getPoints(), winnerId));
        } else if (matchScore.getPoints().get(winnerId) - matchScore.getPoints().get(looserId) == 2) {
            matchScore.setSet1(changeSetScore(matchScore.getSet1(), winnerId));
            matchScore.setPoints(resetPoints(matchScore.getPoints()));
        }

        return matchScore;
    }


    private static Map<Integer, Integer> changePoints(Map<Integer, Integer> currentPoints, Integer winnerId) {
        Integer currentScore = currentPoints.get(winnerId);
        currentPoints.put(winnerId, currentScore + 1);
        return currentPoints;
    }


    private static Map<Integer, Integer> resetPoints(Map<Integer, Integer> currentPoints) {
        for (Map.Entry<Integer, Integer> entry : currentPoints.entrySet()) {
            currentPoints.put(entry.getKey(), 0);
        }
        return currentPoints;
    }


    private static Map<Integer, Integer> changeSetScore(Map<Integer, Integer> currentSetScore, Integer winnerId) {
        Integer currentScore = currentSetScore.get(winnerId);
        currentSetScore.put(winnerId, currentScore + 1);
        return currentSetScore;
    }
}
