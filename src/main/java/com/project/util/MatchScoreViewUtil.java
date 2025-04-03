package com.project.util;

import com.project.entity.matchState.Score;
import jakarta.servlet.http.HttpServletRequest;

public class MatchScoreViewUtil {

    public HttpServletRequest getFilledRequest (HttpServletRequest req, Score match) {
        req.setAttribute(Player.ONE.getName(), "%s".formatted(match.getPlayer1Name()));
        req.setAttribute(Player.TWO.getName(), "%s".formatted(match.getPlayer2Name()));

        req.setAttribute("set1ScorePlayer1", "%s".formatted(match.getSetScore(Player.ONE.getName(), SetNumber.ONE.getName())));
        req.setAttribute("set1ScorePlayer2", "%s".formatted(match.getSetScore(Player.TWO.getName(), SetNumber.ONE.getName())));

        req.setAttribute("set2ScorePlayer1", "%s".formatted(match.getSetScore(Player.ONE.getName(), SetNumber.TWO.getName())));
        req.setAttribute("set2ScorePlayer2", "%s".formatted(match.getSetScore(Player.TWO.getName(), SetNumber.TWO.getName())));

        req.setAttribute("set3ScorePlayer1", "%s".formatted(match.getSetScore(Player.ONE.getName(), SetNumber.THREE.getName())));
        req.setAttribute("set3ScorePlayer2", "%s".formatted(match.getSetScore(Player.TWO.getName(), SetNumber.THREE.getName())));

        req.setAttribute("pointsScorePlayer1", "%s".formatted(match.getPointsForDisplay(Player.ONE.getName())));
        req.setAttribute("pointsScorePlayer2", "%s".formatted(match.getPointsForDisplay(Player.TWO.getName())));

        if (match.isMatchOver()) {
            req.setAttribute("winnerName", match.getWinnerName());
        }

        return req;
    }
}
