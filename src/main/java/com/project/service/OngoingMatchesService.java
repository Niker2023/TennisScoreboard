package com.project.service;

import com.project.entity.MatchScore;

import java.util.*;

public class OngoingMatchesService {

    private static final Map<UUID, MatchScore> matches = new HashMap<>();

    public static UUID addMatch(MatchScore matchScore) {
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, matchScore);
        return uuid;
    }

    public static MatchScore getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public static void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
