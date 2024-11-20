package com.project.util;

import com.project.dao.PlayerDao;
import com.project.entity.MatchScore;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class MatchScoreViewUtil {

    public static HttpServletRequest getFilledRequest (HttpServletRequest req, UUID uuid, MatchScore match) {

        Integer player1Id = match.getPlayer1Id();
        Integer player2Id = match.getPlayer2Id();

        String player1 = PlayerDao.getInstance().getNameById(player1Id);
        String player2 = PlayerDao.getInstance().getNameById(player2Id);

        req.setAttribute("UUID", "%s".formatted(uuid));

        req.setAttribute("player1", "%s".formatted(player1));
        req.setAttribute("player2", "%s".formatted(player2));

        Integer set1ScorePlayer1 = match.getSet1().get(player1Id);
        Integer set1ScorePlayer2 = match.getSet1().get(player2Id);

        req.setAttribute("set1ScorePlayer1", "%s".formatted(set1ScorePlayer1));
        req.setAttribute("set1ScorePlayer2", "%s".formatted(set1ScorePlayer2));

        var set2ScorePlayer1 = match.getSet2().get(player1Id);
        var set2ScorePlayer2 = match.getSet2().get(player2Id);

        req.setAttribute("set2ScorePlayer1", "%s".formatted(set2ScorePlayer1));
        req.setAttribute("set2ScorePlayer2", "%s".formatted(set2ScorePlayer2));

        var set3ScorePlayer1 = match.getSet3().get(player1Id);
        var set3ScorePlayer2 = match.getSet3().get(player2Id);

        req.setAttribute("set3ScorePlayer1", "%s".formatted(set3ScorePlayer1));
        req.setAttribute("set3ScorePlayer2", "%s".formatted(set3ScorePlayer2));

        var pointsScorePlayer1 = match.getPoints().get(player1Id);
        var pointsScorePlayer2 = match.getPoints().get(player2Id);

        if (pointsScorePlayer1 < 4 && pointsScorePlayer2 < 4) {
            req.setAttribute("pointsScorePlayer2", "%s".formatted(pointsView(pointsScorePlayer2)));
            req.setAttribute("pointsScorePlayer1", "%s".formatted(pointsView(pointsScorePlayer1)));
        }

        return req;
    }


    private static String pointsView(Integer points) {

        if (points == 1) {
            return "15";
        } else if (points == 2) {
            return "30";
        } else if (points == 3) {
            return "40";
        }
        return "0";
    }

}
