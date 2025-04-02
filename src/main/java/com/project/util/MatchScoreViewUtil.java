package com.project.util;

import com.project.entity.MatchScore;
import com.project.entity.matchState.Score;
import jakarta.servlet.http.HttpServletRequest;

public class MatchScoreViewUtil {

    public HttpServletRequest getFilledRequest (HttpServletRequest req, Score match) {
        req.setAttribute(Player.ONE.getName(), "%s".formatted(match.getPlayer1Name()));
        req.setAttribute(Player.TWO.getName(), "%s".formatted(match.getPlayer2Name()));

        Integer set1ScorePlayer1 = match.getSet1Score(Player.ONE.getName());
        Integer set1ScorePlayer2 = match.getSet1Score(Player.TWO.getName());

        req.setAttribute("set1ScorePlayer1", "%s".formatted(set1ScorePlayer1));
        req.setAttribute("set1ScorePlayer2", "%s".formatted(set1ScorePlayer2));

        var set2ScorePlayer1 = match.getSet2Score(Player.ONE.getName());
        var set2ScorePlayer2 = match.getSet2Score(Player.TWO.getName());

        req.setAttribute("set2ScorePlayer1", "%s".formatted(set2ScorePlayer1));
        req.setAttribute("set2ScorePlayer2", "%s".formatted(set2ScorePlayer2));

        var set3ScorePlayer1 = match.getSet3Score(Player.ONE.getName());
        var set3ScorePlayer2 = match.getSet3Score(Player.TWO.getName());

        req.setAttribute("set3ScorePlayer1", "%s".formatted(set3ScorePlayer1));
        req.setAttribute("set3ScorePlayer2", "%s".formatted(set3ScorePlayer2));

        req.setAttribute("pointsScorePlayer1", "%s".formatted(match.getPoints(Player.ONE.getName())));
        req.setAttribute("pointsScorePlayer2", "%s".formatted(match.getPoints(Player.TWO.getName())));

        if (match.isMatchOver()) {
            req.setAttribute("winnerName", match.getWinnerName());
        }
//        req.setAttribute("winnerName", winnerName(match));

        return req;
    }

//    private String winnerName(Score matchScore) {
//        if (matchScore.getWinnerId() == 0) {
//            return "0";
//        } else {
//            if (matchScore.getWinnerId().equals(matchScore.getPlayer1Id())) {
//                return matchScore.getPlayer1Name();
//            } else {
//                return matchScore.getPlayer2Name();
//            }
//        }
//    }
//
//    private String pointsView(Score match, String player1) {
//        String player2;
//        if ("player1".equals(player1)) {
//            player2 = "player2";
//        } else {
//            player2 = "player1";
//        }
//        if (isTiebreak(match)) {
//            return match.getPoints(player1).toString();
//        } else if (match.getPoints(player1) > 2 && match.getPoints(player2) > 2) {
//            if (match.getPoints(player2) - match.getPoints(player1) == 1) {
//                return "<br>";
//            } else if (match.getPoints(player1) - match.getPoints(player2) == 1) {
//                return "AD";
//            }
//            return "40";
//        } else if (match.getPoints(player1) == 1) {
//            return "15";
//        } else if (match.getPoints(player1) == 2) {
//            return "30";
//        } else if (match.getPoints(player1) == 3) {
//            return "40";
//        }
//        return "0";
//    }
//
//    private boolean isTiebreak(Score match) {
//        return (match.getSet1Score("player1") == 6 && match.getSet1Score("player2") == 6)
//                || (match.getSet2Score("player1") == 6 && match.getSet2Score("player2") == 6)
//                || (match.getSet3Score("player1") == 6 && match.getSet3Score("player2") == 6);
//    }
}
