package com.emnuelht.TaskAuto.Model;

import jakarta.validation.constraints.NotBlank;

public record InputRequest(
        @NotBlank(message = "é obrigatório") String text
) {
}
