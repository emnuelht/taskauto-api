package com.emnuelht.TaskAuto.Service;

import com.emnuelht.TaskAuto.Enum.InputType;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InputDetectorService {
    private final OllamaChatModel chatModel;

    private static final String DETECTOR_PROMPT = """
            Classifique o texto abaixo em uma dessas categorias: TAREFA, META, DIARIO, OBSERVACAO, DESCONHECIDO.
            Responda SOMENTE com a palavra, sem pontuação, sem explicação.
            
            Texto: "%s"
            """;

    public InputType detector(String text) {
        String prompt = String.format(DETECTOR_PROMPT, text);
        String response = chatModel.call(prompt).trim().toUpperCase();
        try {
            return InputType.valueOf(response);
        } catch (IllegalArgumentException e) {
            return InputType.DESCONHECIDO;
        }
    }
}
