package com.emnuelht.TaskAuto.Controller;

import com.emnuelht.TaskAuto.Model.*;
import com.emnuelht.TaskAuto.Service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

    // Group
    @PostMapping("/group")
    public ResponseEntity<?> addGroup(@RequestBody GroupAddRequest request) {
        notesService.addGroup(request);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PutMapping("/group")
    public ResponseEntity<?> updateGroup(@RequestBody GroupUpdateRequest request) {
        notesService.updateGroup(request);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @DeleteMapping("/group/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable String groupId) {
        notesService.deleteGroup(groupId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/groups")
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok(ApiResponse.success(notesService.getGroups()));
    }

    // Note
    @PostMapping("/note")
    public ResponseEntity<?> addNote(@RequestBody NoteAddRequest request) {
        notesService.addNote(request);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PutMapping("/note/{noteId}")
    public ResponseEntity<?> updateNote(@RequestBody NoteUpdateRequest request) {
        notesService.updateNote(request);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @DeleteMapping("/note/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable String noteId) {
        notesService.deleteNote(noteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/notes/{groupId}")
    public ResponseEntity<?> getNotes(@PathVariable String groupId) {
        return ResponseEntity.ok(ApiResponse.success(notesService.getNotes(groupId)));
    }
}