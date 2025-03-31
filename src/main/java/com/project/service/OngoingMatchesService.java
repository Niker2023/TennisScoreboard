package com.project.service;

import com.project.entity.MatchScore;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, MatchScore> matches = new ConcurrentHashMap<>();

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