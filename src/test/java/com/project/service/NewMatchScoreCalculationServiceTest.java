package com.project.service;

import com.project.dto.PlayerDto;
import com.project.entity.matchState.Score;
import com.project.util.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewMatchScoreCalculationServiceTest {

    @Test
    void whenPlayer1WinsPoint_thenSuccess() {
        Score testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.setPoints(Player.ONE.getName(),3);
        testMatch.setPoints(Player.TWO.getName(),3);

        testMatch.playerWinsPointByPlayerOrder(Player.ONE.getName());

        Score expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints(Player.ONE.getName(),4);
        expectedMatchResult.setPoints(Player.TWO.getName(),3);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer2WinsPointAndGameWithFirstAD_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",4);

        testMatch.playerWinsPointByPlayerOrder(Player.TWO.getName());

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player2", 1);

         assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer1WinsPointAndGameWith_40_0_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",0);

        testMatch.playerWinsPointByPlayerOrder(Player.ONE.getName());

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setSet1Score("player1", 1);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer2WinsPointAndGameWithMoreThen4PointsAndAD_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.setPoints("player1",5);
        testMatch.setPoints("player2",6);
        testMatch.playerWinsPointByPlayerOrder(Player.TWO.getName());

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player2", 1);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer1WinsPointAndGameAndSet1_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",2);
        testMatch.setSet1Score("player1", 5);
        testMatch.setSet1Score("player2", 4);

        testMatch.playerWinsPointByPlayerOrder(Player.ONE.getName());

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player1", 6);
        expectedMatchResult.setSet1Score("player2", 4);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer2WinsPointAndGameAndSet1AndGameInSet2_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.setPoints("player2",3);
        testMatch.setPoints("player1",2);
        testMatch.setSet1Score("player2", 5);
        testMatch.setSet1Score("player1", 4);

        for (int i = 0; i < 5; i++) {
            testMatch.playerWinsPointByPlayerOrder(Player.TWO.getName());
        }

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player1", 4);
        expectedMatchResult.setSet1Score("player2", 6);
        expectedMatchResult.setSet2Score("player2", 1);
        expectedMatchResult.setSet2Score("player1", 0);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenScoreInSet1_5_6_AndPlayer1WinsGame_thenTiebreak_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.setPoints("player1",0);
        testMatch.setPoints("player2",0);
        testMatch.setSet1Score("player1", 5);
        testMatch.setSet1Score("player2", 6);
        for (int i = 0; i < 9; i++) {
            testMatch.playerWinsPointByPlayerOrder(Player.ONE.getName());
        }

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",5);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player1", 6);
        expectedMatchResult.setSet1Score("player2", 6);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenTiebreakAndPlayer1Wins10ScoresInSet2_thenStartSet3_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.SetStatesForTests(2,true);

        testMatch.setPoints("player1",0);
        testMatch.setPoints("player2",0);
        testMatch.setSet1Score("player1", 5);
        testMatch.setSet1Score("player2", 7);
        testMatch.setSet2Score("player1", 6);
        testMatch.setSet2Score("player2", 6);
        for (int i = 0; i < 11; i++) {
            testMatch.playerWinsPointByPlayerOrder(Player.ONE.getName());
        }

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",0);
        expectedMatchResult.setSet1Score("player1", 5);
        expectedMatchResult.setSet1Score("player2", 7);
        expectedMatchResult.setSet2Score("player1", 7);
        expectedMatchResult.setSet2Score("player2", 6);
        expectedMatchResult.setSet3Score("player1", 1);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenTiebreakAndPlayer2Wins2PointsWhen_7_7_thenPlayer2WinsTiebreak_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.SetStatesForTests(2, true);

        testMatch.setPoints("player1",7);
        testMatch.setPoints("player2",7);
        testMatch.setSet1Score("player1", 7);
        testMatch.setSet1Score("player2", 5);
        testMatch.setSet2Score("player1", 6);
        testMatch.setSet2Score("player2", 6);
        for (int i = 0; i < 4; i++) {
            testMatch.playerWinsPointByPlayerOrder(Player.TWO.getName());
        }

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setPoints("player1",0);
        expectedMatchResult.setPoints("player2",2);
        expectedMatchResult.setSet1Score("player1", 7);
        expectedMatchResult.setSet1Score("player2", 5);
        expectedMatchResult.setSet2Score("player1", 6);
        expectedMatchResult.setSet2Score("player2", 7);

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer1WinsGameAndMatch_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.SetStatesForTests(2, false);

        testMatch.setPoints("player1",3);
        testMatch.setPoints("player2",2);
        testMatch.setSet1Score("player1", 7);
        testMatch.setSet1Score("player2", 6);
        testMatch.setSet2Score("player1", 5);
        testMatch.setSet2Score("player2", 4);

        testMatch.playerWinsPointByPlayerOrder(Player.ONE.getName());

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));
        expectedMatchResult.setSet1Score("player1", 7);
        expectedMatchResult.setSet1Score("player2", 6);
        expectedMatchResult.setSet2Score("player1", 6);
        expectedMatchResult.setSet2Score("player2", 4);

        expectedMatchResult.setWinnerId(Player.ONE.getName());

        assertEquals(testMatch, expectedMatchResult);
    }

    @Test
    void whenPlayer2WinsTiebreakInSet3_thenSuccess() {
        var testMatch = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        testMatch.SetStatesForTests(3, true);

        testMatch.setPoints("player1",7);
        testMatch.setPoints("player2",7);
        testMatch.setSet1Score("player1", 7);
        testMatch.setSet1Score("player2", 5);
        testMatch.setSet2Score("player1", 6);
        testMatch.setSet2Score("player2", 7);
        testMatch.setSet3Score("player1", 6);
        testMatch.setSet3Score("player2", 6);

        for (int i = 0; i < 2; i++) {
            testMatch.playerWinsPointByPlayerOrder(Player.TWO.getName());
        }

        var expectedMatchResult = new Score(new PlayerDto(1,""), new PlayerDto(2,""));

        expectedMatchResult.setSet1Score("player1", 7);
        expectedMatchResult.setSet1Score("player2", 5);
        expectedMatchResult.setSet2Score("player1", 6);
        expectedMatchResult.setSet2Score("player2", 7);
        expectedMatchResult.setSet3Score("player1", 6);
        expectedMatchResult.setSet3Score("player2", 7);

        expectedMatchResult.setWinnerId(Player.TWO.getName());

        assertEquals(testMatch, expectedMatchResult);
    }
}
