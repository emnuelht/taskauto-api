package com.emnuelht.TaskAuto.Repository;

import com.emnuelht.TaskAuto.Entity.GroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupsRepository extends JpaRepository<GroupsEntity, String> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
}
