package com.project.util;

public enum Set {
    ONE {
        @Override
        public String toString() {
            return "SetOneState";
        }
    },
    TWO {
        @Override
        public String toString() {
            return "SetTwoState";
        }
    },
    THREE {
        @Override
        public String toString() {
            return "SetThreeState";
        }
    }
}
