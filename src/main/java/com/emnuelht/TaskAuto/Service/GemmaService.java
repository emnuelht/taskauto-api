package com.emnuelht.TaskAuto.Service;

import com.emnuelht.TaskAuto.Engine.PromptTemplateEngine;
import com.emnuelht.TaskAuto.Enum.InputType;
import com.emnuelht.TaskAuto.Model.IAResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GemmaService {
    private final OllamaChatModel chatModel;
    private final PromptTemplateEngine templateEngine;
    private final InputDetectorService inputDetectorService;
    private final ObjectMapper objectMapper;

    public IAResponse process(String text) {
        InputType type = inputDetectorService.detector(text);
        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String systemContent = templateEngine.buildPrompt(type, dateNow);
        SystemMessage system = new SystemMessage(systemContent);
        UserMessage user = new UserMessage(text);

        OllamaOptions options = new OllamaOptions();
        options.setTemperature(0.0);
        options.setFormat("json");

        String raw = chatModel.call(new Prompt(List.of(system, user), options))
                .getResult().getOutput().getText()
                .trim();

        return parseJson(raw);
    }

    private IAResponse parseJson(String raw) {
        try {
            String json = raw
                    .replaceAll("(?s)```json\\s*", "")
                    .replaceAll("(?s)```\\s*", "")
                    .trim();

            return objectMapper.readValue(json, IAResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("IA retornou JSON inválido: " + raw, e);
        }
    }
}
