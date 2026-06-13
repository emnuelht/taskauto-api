package com.emnuelht.TaskAuto.Repository;

import com.emnuelht.TaskAuto.Entity.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, String> {
    List<NotesEntity> findByGroupId(String groupId);
}
