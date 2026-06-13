package com.emnuelht.TaskAuto.Model;

import com.emnuelht.TaskAuto.Enum.InputType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record NoteUpdateRequest(
        @NotBlank(message = "é obrigatório")
        String groupId,

        @NotBlank(message = "é obrigatório")
        String noteId,

        @NotBlank(message = "é obrigatório")
        String title,

        @NotBlank(message = "é obrigatório")
        String summary,

        @NotBlank(message = "é obrigatório")
        String expandedContent,

        @NotNull(message = "é obrigatório")
        List<String> extractedItems,

        @NotNull(message = "é obrigatório")
        LocalDateTime inferredDeadline,

        @NotNull(message = "é obrigatório")
        List<String> autoTags,

        @NotNull(message = "é obrigatório")
        List<String> nextSteps,

        @NotNull(message = "é obrigatório")
        InputType detectorType
) {
}
