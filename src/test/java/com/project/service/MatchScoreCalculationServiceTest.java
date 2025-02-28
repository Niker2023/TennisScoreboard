package com.project.service;

import com.project.entity.MatchScore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatchScoreCalculationServiceTest {

    @Test
    void calculateWinPointPlayer1() {
        var testMatch = new MatchScore(0, 1);
        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",3);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player1");

        var expectedMatchResult = new MatchScore(0, 1);
        expectedMatchResult.setPoints("player1",4);
        expectedMatchResult.setPoints("player2",3);
        assertEquals(testMatch, expectedMatchResult);
    }


    @Test
    void calculateWinSetPlayer2() {
        var testMatch = new MatchScore(0, 1);
        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",4);
        MatchScoreCalculationService.changeMatchScore(testMatch, "player2");
        
        var expectedMatchResult = new MatchScore(0, 1);
        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player1", 0);
        expectedMatchResult.setSet1Score("player2", 1);
        assertEquals(testMatch, expectedMatchResult);
    }
}
