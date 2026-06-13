package com.emnuelht.TaskAuto.Model;

import jakarta.validation.constraints.NotBlank;

public record GroupUpdateRequest(
        @NotBlank(message = "é obrigatório")
        String groupId,

        @NotBlank(message = "é obrigatório")
        String groupName
) {
}
