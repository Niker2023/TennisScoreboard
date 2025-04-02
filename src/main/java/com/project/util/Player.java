package com.project.util;

import lombok.Getter;

@Getter
public enum Player {
    ONE("player1"),
    TWO ("player2");

    private final String name;

    Player(String name) {
        this.name = name;
    }

}

