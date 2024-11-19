package com.project.entity;

import com.project.dto.PlayerDto;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class MatchScore {

    Integer player1Id;
    Integer player2Id;
    private Map<Integer, Integer> points = new HashMap<>();
    private Map<Integer, Integer> set1 = new HashMap<>();
    private Map<Integer, Integer> set2 = new HashMap<>();
    private Map<Integer, Integer> set3 = new HashMap<>();

    public MatchScore(Integer player1, Integer player2) {
        this.player1Id = player1;
        this.player2Id = player2;
        this.points.put(player1, 0);
        this.points.put(player2, 0);
        this.set1.put(player1, 0);
        this.set1.put(player2, 0);
        this.set2.put(player1, 0);
        this.set2.put(player2, 0);
        this.set3.put(player1, 0);
        this.set3.put(player2, 0);
    }
}
