package com.project.service;

import com.project.entity.match_state.Score;
import com.project.exception.MatchNotFoundException;

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
        if (matches.containsKey(uuid)) {
            return matches.get(uuid);
        }
        throw new MatchNotFoundException("Матча с данныйм UUID " + uuid.toString() + " не существует или он уже доигран");
    }

    public void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}