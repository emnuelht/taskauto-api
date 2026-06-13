package com.emnuelht.TaskAuto.Model;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "é obrigatório")
        String username,
        @NotBlank(message = "é obrigatório")
        String password
) {
}
