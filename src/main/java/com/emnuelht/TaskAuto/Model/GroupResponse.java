package com.emnuelht.TaskAuto.Model;

import java.time.LocalDateTime;
import java.util.List;

public record GroupResponse(
        String id,
        String name,
        LocalDateTime createdAt,
        List<NoteResponse> notes
) {}