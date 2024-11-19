package com.project.service;

import com.project.entity.MatchScore;

import java.util.*;

public class OngoingMatchesService {

    private static final Map<UUID, MatchScore> matches = new HashMap<>();

    public static void addMatch(UUID uuid, MatchScore matchScore) {
        matches.put(uuid, matchScore);
    }

    public static MatchScore getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public static void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
