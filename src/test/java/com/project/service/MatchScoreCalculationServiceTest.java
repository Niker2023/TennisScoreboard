package com.project.service;

import com.project.entity.MatchScore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatchScoreCalculationServiceTest {

    @Test
    void whenPlayer1WinsPoint_thenSuccess() {
        var testMatch = new MatchScore(1, 2);
        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",3);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");

        var expectedMatchResult = new MatchScore(1, 2);
        expectedMatchResult.setPoints("player1",4);
        expectedMatchResult.setPoints("player2",3);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer2WinsPointAndGameWithFirstAD_thenSuccess() {
        var testMatch = new MatchScore(0, 1);
        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",4);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");

        var expectedMatchResult = new MatchScore(0, 1);
        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player2", 1);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer1WinsPointAndGameWith_40_0_thenSuccess() {
        var testMatch = new MatchScore(1, 2);
        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",0);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");

        var expectedMatchResult = new MatchScore(1, 2);
        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setSet1Score("player1", 1);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer2WinsPointAndGameWithMoreThen4PointsAndAD_thenSuccess() {
        var testMatch = new MatchScore(1, 2);
        testMatch.setPoints("player1",5);
        testMatch.setPoints("player2",6);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");

        var expectedMatchResult = new MatchScore(1, 2);
        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player2", 1);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer1WinsPointAndGameAndSet1_thenSuccess() {
        var testMatch = new MatchScore(1, 2);
        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",2);
        testMatch.setSet1Score("player1", 5);
        testMatch.setSet1Score("player2", 4);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");

        var expectedMatchResult = new MatchScore(1, 2);
        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player1", 6);
        expectedMatchResult.setSet1Score("player2", 4);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer2WinsPointAndGameAndSet1AndGameInSet2_thenSuccess() {
        var testMatch = new MatchScore(1, 2);
        testMatch.setPoints("player2",3);
        testMatch.setPoints("player1",2);
        testMatch.setSet1Score("player2", 5);
        testMatch.setSet1Score("player1", 4);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");


        var expectedMatchResult = new MatchScore(1, 2);
        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player1", 4);
        expectedMatchResult.setSet1Score("player2", 6);
        expectedMatchResult.setSet2Score("player2", 1);
        expectedMatchResult.setSet2Score("player1", 0);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer1WinsPointAndGameAndSet2AndGameInSet3_thenSuccess() {
        var testMatch = new MatchScore(1, 2);
        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",2);
        testMatch.setSet1Score("player1", 7);
        testMatch.setSet1Score("player2", 6);
        testMatch.setSet2Score("player1", 5);
        testMatch.setSet2Score("player2", 4);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");


        var expectedMatchResult = new MatchScore(1, 2);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setSet1Score("player2", 6);
        expectedMatchResult.setSet1Score("player1", 7);
        expectedMatchResult.setSet2Score("player1", 6);
        expectedMatchResult.setSet2Score("player2", 4);
        expectedMatchResult.setSet3Score("player1", 1);

        assertEquals(testMatch, expectedMatchResult);
    }
}
