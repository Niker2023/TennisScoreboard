package com.project.service;

import com.project.entity.match_state.Score;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, Score> matches = new ConcurrentHashMap<>();

    public UUID addMatch(Score matchScore) {
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, matchScore);
        return uuid;
    }

    public Score getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}