package com.project.service;

import com.project.entity.MatchScore;

import java.util.*;

public class OngoingMatchesService {

    private static final Map<UUID, MatchScore> matches = new HashMap<>();

    public synchronized UUID addMatch(MatchScore matchScore) {
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, matchScore);
        return uuid;
    }

    public synchronized MatchScore getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public synchronized void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}