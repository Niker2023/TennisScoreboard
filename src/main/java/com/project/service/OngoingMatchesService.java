package com.project.service;

import com.project.entity.match_state.Score;
import com.project.exception.MatchNotFoundException;
import com.project.util.ValidationUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, Score> matches = new ConcurrentHashMap<>();

    public UUID addMatch(Score matchScore) {
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, matchScore);
        return uuid;
    }


    public Score getMatch(String uuid) {

        ValidationUtil.isUUID(uuid);

        var currentUuid = getUuidFromString(uuid);
        if (matches.containsKey(currentUuid)) {
            return matches.get(currentUuid);
        }
        throw new MatchNotFoundException("Матча с данным UUID " + uuid + " не существует или он уже доигран");
    }


    public void removeMatch(String uuid) {
        var currentUuid = getUuidFromString(uuid);
        matches.remove(currentUuid);
    }


    private UUID getUuidFromString(String uuid) {
        ValidationUtil.isUUID(uuid);
        return UUID.fromString(uuid);
    }
}