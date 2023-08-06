package com.example.demoaccessvalidator.repository;

import com.example.demoaccessvalidator.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {

    @Query(value = "SELECT CAST(user_id as varchar) user_id FROM user_group WHERE user_id = ?1 AND group_id = ?2", nativeQuery = true)
    Optional<UUID> findUserIdsByGroupId(UUID userId, UUID groupId);
    Optional<Group> findByName(String name);
}
