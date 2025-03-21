package com.project.dto;

import lombok.Value;

@Value
public class FinishedMatchDto {
    String player1;
    String player2;
    String winner;
}