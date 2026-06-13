package com.emnuelht.TaskAuto.Controller;

import com.emnuelht.TaskAuto.Model.ApiResponse;
import com.emnuelht.TaskAuto.Model.InputRequest;
import com.emnuelht.TaskAuto.Service.GemmaService;
import com.emnuelht.TaskAuto.Service.SerializerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/input")
@RequiredArgsConstructor
public class ChatController {
    private final GemmaService gemmaService;

    @PostMapping
    public ResponseEntity<?> process(@Valid @RequestBody InputRequest request) {
        String textSerialized = SerializerService.promptSerialize(request.text());
        return ResponseEntity.ok(ApiResponse.success(gemmaService.process(textSerialized)));
    }
}