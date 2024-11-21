package com.project.service;

import com.project.entity.MatchScore;
import com.project.util.Player;

import static java.lang.Math.abs;

public class MatchScoreCalculationService {

    private static String winner;
    private static String loser;

    public static void calculate(MatchScore matchScore, String winningPlayer) {

        winner = winningPlayer;
        if (winningPlayer.equals(Player.ONE.toString())) {
            loser = Player.TWO.toString();
        } else {
            loser = Player.ONE.toString();
        }

        if (checkVictoryInGame(matchScore)) {
            matchScore.setSet1Score(winner, matchScore.getSet1Score(winner) + 1);
            matchScore.setPoints(winner, 0);
            matchScore.setPoints(loser, 0);
        } else {
            matchScore.setPoints(winner, matchScore.getPoints(winner) + 1);
        }
    }


    private static boolean checkVictoryInGame(MatchScore matchScore) {
        return matchScore.getPoints(winner) > 2 && abs(matchScore.getPoints(winner) + 1 - matchScore.getPoints(loser)) > 1;
    }
}
