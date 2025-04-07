package com.project.dto;

import java.util.List;

public record MatchesToDisplayDto(
        int currentPage,
        int totalPages,
        int initialDigitOfPageNumber,
        List<FinishedMatchDto> finishedMatchDto) {}
