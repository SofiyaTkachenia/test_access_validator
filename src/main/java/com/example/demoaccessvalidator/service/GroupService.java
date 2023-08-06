package com.example.demoaccessvalidator.service;

import com.example.demoaccessvalidator.entity.Board;
import com.example.demoaccessvalidator.entity.Group;
import com.example.demoaccessvalidator.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    public Group createGroup(Group group) {
        if (group.getName().isEmpty()) {
            throw new RuntimeException("Group Name cannot be empty");
        }
        groupRepository.findByName(group.getName()).ifPresent(existingGroup -> {
            throw new RuntimeException("Group with the same name already exists");
        });
        return groupRepository.save(group);
    }

    public Group getGroupById(UUID id) {
        return groupRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find this group"));
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public void deleteGroup(UUID id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        }
    }

}
