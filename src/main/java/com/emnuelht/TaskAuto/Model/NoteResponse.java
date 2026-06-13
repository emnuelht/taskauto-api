package com.emnuelht.TaskAuto.Model;

import com.emnuelht.TaskAuto.Enum.InputType;

import java.time.LocalDateTime;
import java.util.List;

public record NoteResponse(
        String id,
        String title,
        String summary,
        String expandedContent,
        List<String> extractedItems,
        LocalDateTime inferredDeadline,
        List<String> autoTags,
        List<String> nextSteps,
        InputType detectorType,
        LocalDateTime createdAt
) {}
