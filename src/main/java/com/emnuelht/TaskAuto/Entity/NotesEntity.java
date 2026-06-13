package com.emnuelht.TaskAuto.Entity;

import com.emnuelht.TaskAuto.Enum.InputType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotesEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupsEntity group;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "expanded_content", columnDefinition = "TEXT")
    private String expandedContent;

    @Column(name = "extracted_items")
    private List<String> extractedItems;

    @Column(name = "inferred_deadline")
    private LocalDateTime inferredDeadline;

    @Column(name = "auto_tags")
    private List<String> autoTags;

    @Column(name = "next_steps")
    private List<String> nextSteps;

    @Column(name = "detector_type")
    private InputType detectorType;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
