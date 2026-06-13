package com.emnuelht.TaskAuto.Model;

import com.emnuelht.TaskAuto.Enum.InputType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IAResponse(
        String title,
        String summary,
        String expandedContent,
        List<String> extractedItems,
        String inferredDeadline,
        List<String> autoTags,
        List<String> nextSteps,
        InputType detectorType
) {}
