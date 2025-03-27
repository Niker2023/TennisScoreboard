package com.project.service;

import com.project.entity.MatchScore;

import java.util.*;

public class OngoingMatchesService {

    private final Map<UUID, MatchScore> matches = new HashMap<>();

    public UUID addMatch(MatchScore matchScore) {
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, matchScore);
        return uuid;
    }

    public MatchScore getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
