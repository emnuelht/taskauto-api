package com.emnuelht.TaskAuto.Model;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "é obrigatório")
        String username,
        @NotBlank(message = "é obrigatório")
        String password
) {
}
