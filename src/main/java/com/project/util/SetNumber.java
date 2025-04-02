package com.project.util;

public enum SetNumber {
    ONE ("SetOneState"),
    TWO ("SetTwoState"),
    THREE ("SetThreeState");

    private final String setName;

    SetNumber(String setName) {
        this.setName = setName;
    }

    public String getName() {
        return setName;
    }

    public static SetNumber getSetNumberByName(String setName) {
        for (SetNumber setNumber : SetNumber.values()) {
            if (setNumber.getName().equals(setName)) {
                return setNumber;
            }
        }
        throw new IllegalArgumentException("Неизвестное описание: " + setName);
    }
}
