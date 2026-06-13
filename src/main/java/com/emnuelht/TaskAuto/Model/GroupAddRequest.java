package com.emnuelht.TaskAuto.Model;

import jakarta.validation.constraints.NotBlank;

public record GroupAddRequest(
        @NotBlank(message = "é obrigatório")
        String groupName
) {
}
