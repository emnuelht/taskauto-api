package com.emnuelht.TaskAuto.Service;

import com.emnuelht.TaskAuto.Entity.GroupsEntity;
import com.emnuelht.TaskAuto.Entity.NotesEntity;
import com.emnuelht.TaskAuto.Model.*;
import com.emnuelht.TaskAuto.Repository.GroupsRepository;
import com.emnuelht.TaskAuto.Repository.NotesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {
    private final GroupsRepository groupsRepository;
    private final NotesRepository notesRepository;

    @Transactional
    public void addGroup(GroupAddRequest request) {
        if (groupsRepository.existsByName(request.groupName())) {
            throw new RuntimeException("Group with name " + request.groupName() + " already exists");
        }
        GroupsEntity group = new GroupsEntity();
        group.setName(request.groupName());
        groupsRepository.save(group);
    }

    @Transactional
    public void updateGroup(GroupUpdateRequest request) {
        GroupsEntity group = groupsRepository.findById(request.groupId())
                .orElseThrow(() -> new RuntimeException("Group with id " + request.groupId() + " not found"));

        if (groupsRepository.existsByNameAndIdNot(request.groupName(), request.groupId())) {
            throw new RuntimeException("Group with name " + request.groupName() + " already exists");
        }

        group.setName(request.groupName());
        groupsRepository.save(group);
    }

    @Transactional
    public void deleteGroup(String groupId) {
        GroupsEntity group = groupsRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group with id " + groupId + " not found"));

        groupsRepository.delete(group);  // cascade apaga as notes automaticamente
    }

    @Transactional
    public void addNote(NoteAddRequest request) {
        GroupsEntity group = groupsRepository.findById(request.groupId())
                .orElseThrow(() -> new RuntimeException("Group with id " + request.groupId() + " not found"));

        NotesEntity note = NotesEntity.builder()
                .group(group)
                .title(request.title())
                .summary(request.summary())
                .expandedContent(request.expandedContent())
                .extractedItems(request.extractedItems())
                .inferredDeadline(request.inferredDeadline())
                .autoTags(request.autoTags())
                .nextSteps(request.nextSteps())
                .detectorType(request.detectorType())
                .build();

        notesRepository.save(note);
    }

    @Transactional
    public void updateNote(NoteUpdateRequest request) {
        NotesEntity note = notesRepository.findById(request.noteId())
                .orElseThrow(() -> new RuntimeException("Note with id " + request.noteId() + " not found"));

        // troca de grupo se necessário
        if (!note.getGroup().getId().equals(request.groupId())) {
            GroupsEntity novoGroup = groupsRepository.findById(request.groupId())
                    .orElseThrow(() -> new RuntimeException("Group with id " + request.groupId() + " not found"));
            note.setGroup(novoGroup);
        }

        note.setTitle(request.title());
        note.setSummary(request.summary());
        note.setExpandedContent(request.expandedContent());
        note.setExtractedItems(request.extractedItems());
        note.setInferredDeadline(request.inferredDeadline());
        note.setAutoTags(request.autoTags());
        note.setNextSteps(request.nextSteps());
        note.setDetectorType(request.detectorType());

        notesRepository.save(note);
    }

    @Transactional
    public void deleteNote(String noteId) {
        NotesEntity note = notesRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note with id " + noteId + " not found"));

        notesRepository.delete(note);
    }

    public List<GroupResponse> getGroups() {
        return groupsRepository.findAll()
                .stream()
                .map(g -> new GroupResponse(
                        g.getId(),
                        g.getName(),
                        g.getCreatedAt(),
                        g.getNotes().stream().map(n -> new NoteResponse(
                                n.getId(),
                                n.getTitle(),
                                n.getSummary(),
                                n.getExpandedContent(),
                                n.getExtractedItems(),
                                n.getInferredDeadline(),
                                n.getAutoTags(),
                                n.getNextSteps(),
                                n.getDetectorType(),
                                n.getCreatedAt()
                        )).toList()
                ))
                .toList();
    }

    public List<NoteResponse> getNotes(String groupId) {
        if (groupsRepository.findById(groupId).isEmpty()) {
            return Collections.emptyList();
        }

        return notesRepository.findByGroupId(groupId)
                .stream()
                .map(n -> new NoteResponse(
                        n.getId(),
                        n.getTitle(),
                        n.getSummary(),
                        n.getExpandedContent(),
                        n.getExtractedItems(),
                        n.getInferredDeadline(),
                        n.getAutoTags(),
                        n.getNextSteps(),
                        n.getDetectorType(),
                        n.getCreatedAt()
                ))
                .toList();
    }
}
