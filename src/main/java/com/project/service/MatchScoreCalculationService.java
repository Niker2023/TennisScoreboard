package com.project.service;

import com.project.entity.MatchScore;
import com.project.util.Player;

public class MatchScoreCalculationService {

    private static String winner;
    private static String loser;

    public static void changeMatchScore(MatchScore matchScore, String winningPlayer) {

        winner = winningPlayer;
        if (winningPlayer.equals(Player.ONE.toString())) {
            loser = Player.TWO.toString();
        } else {
            loser = Player.ONE.toString();
        }

        if (checkTiebreak(matchScore)) {
            if (checkSet1BeingPlayed(matchScore)) {
                if (checkVictoryInTiebreak(matchScore)) {
                    matchScore.setSet1Score(winner, matchScore.getSet1Score(winner) + 1);
                    resetPoints(matchScore);
                } else {
                    matchScore.setPoints(winner, matchScore.getPoints(winner) + 1);
                }
            } else if (checkSet2BeingPlayed(matchScore)) {
                if (checkVictoryInTiebreak(matchScore)) {
                    matchScore.setSet2Score(winner, matchScore.getSet2Score(winner) + 1);
                    resetPoints(matchScore);
                } else {
                    matchScore.setPoints(winner, matchScore.getPoints(winner) + 1);
                }
            } else {
                if (checkVictoryInTiebreak(matchScore)) {
                    matchScore.setSet3Score(winner, matchScore.getSet3Score(winner) + 1);
                    resetPoints(matchScore);
                } else {
                    matchScore.setPoints(winner, matchScore.getPoints(winner) + 1);
                }
            }

        } else if (checkVictoryInGame(matchScore)) {
            if (checkSet1BeingPlayed(matchScore)) {
                matchScore.setSet1Score(winner, matchScore.getSet1Score(winner) + 1);
            } else if (checkSet2BeingPlayed(matchScore)) {
                matchScore.setSet2Score(winner, matchScore.getSet2Score(winner) + 1);
            } else {
                matchScore.setSet3Score(winner, matchScore.getSet3Score(winner) + 1);
            }
            resetPoints(matchScore);
        } else {
            matchScore.setPoints(winner, matchScore.getPoints(winner) + 1);
        }

        if (checkTheEndOfTheMatch(matchScore)) {

        }
    }


    private static void resetPoints(MatchScore matchScore) {
        matchScore.setPoints(winner, 0);
        matchScore.setPoints(loser, 0);
    }


    private static boolean checkVictoryInTiebreak(MatchScore matchScore) {
        if (matchScore.getSet1Score(winner) == 6 && matchScore.getSet1Score(loser) == 6) {
            if (matchScore.getPoints(winner) > 6 && (matchScore.getPoints(winner) - matchScore.getPoints(loser)) > 1) {
                return true;
            }
        } else if (matchScore.getSet2Score(winner) == 6 && matchScore.getSet2Score(loser) == 6) {
            if (matchScore.getPoints(winner) > 6 && (matchScore.getPoints(winner) - matchScore.getPoints(loser)) > 1) {
                return true;
            }
        } else if (matchScore.getSet3Score(winner) == 6 && matchScore.getSet3Score(loser) == 6) {
            if (matchScore.getPoints(winner) > 6 && (matchScore.getPoints(winner) - matchScore.getPoints(loser)) > 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTiebreak(MatchScore matchScore) {
        if ((matchScore.getSet1Score(winner) == 6 && matchScore.getSet1Score(loser) == 6)
                || (matchScore.getSet2Score(winner) == 6 && matchScore.getSet2Score(loser) == 6)
                || (matchScore.getSet3Score(winner) == 6 && matchScore.getSet3Score(loser) == 6)) {
            return true;
        }
        return false;
    }

    private static boolean checkTheEndOfTheMatch(MatchScore matchScore) {
        if ((matchScore.getSet3Score(winner) > 5 && (matchScore.getSet3Score(winner) - matchScore.getSet3Score(loser)) > 1)
                || matchScore.getSet3Score(winner) == 7) {
            return true;
        }
        return false;
    }


    private static boolean checkVictoryInGame(MatchScore matchScore) {
        return matchScore.getPoints(winner) > 2 && (matchScore.getPoints(winner) + 1 - matchScore.getPoints(loser)) > 1;
    }


    private static boolean checkSet1BeingPlayed(MatchScore matchScore) {
        if ((matchScore.getSet1Score(winner) > 5 && (matchScore.getSet1Score(winner) - matchScore.getSet1Score(loser)) > 1)
                || matchScore.getSet1Score(winner) == 7) {
            return false;
        }
        return true;
    }


    private static boolean checkSet2BeingPlayed(MatchScore matchScore) {
        if ((matchScore.getSet2Score(winner) > 5 && (matchScore.getSet2Score(winner) - matchScore.getSet2Score(loser)) > 1)
                || matchScore.getSet2Score(winner) == 7) {
            return false;
        }
        return true;
    }

}
