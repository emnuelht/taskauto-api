package com.emnuelht.TaskAuto.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups", indexes = {
        @Index(name = "idx_name", columnList = "name")
})
@Getter
@Setter
public class GroupsEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotesEntity> notes = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime updatedAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
