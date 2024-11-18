package com.project.dto;

import com.project.entity.Players;

import java.util.UUID;

public record MatchDto(UUID uuid, Players player1, Players player2) {}
