package com.project.util;

public enum Player {
    ONE {
        @Override
        public String toString() {
            return "player1";
        }
    },
    TWO {
        @Override
        public String toString() {
            return "player2";
        }
    }
}

