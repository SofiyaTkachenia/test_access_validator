package com.example.demoaccessvalidator.access_validator;

import static com.example.demoaccessvalidator.access_validator.PrincipalType.GROUP;

import com.example.demoaccessvalidator.entity.Board;
import com.example.demoaccessvalidator.repository.GroupRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Data
@RequiredArgsConstructor
public class AccessValidator {
    private final GroupRepository groupRepository;

    public void validateAccess(UUID userId, Board board, BoardAction action) {
        if (isBoardPublic(board)){
            return;
        }

        AccessPolicy accessPolicy = board.getAccessPolicy();

        for (Statement statement : accessPolicy.getStatements()) {
            boolean isUserStatement = statement.getPrincipalType() == PrincipalType.USER;
            boolean isGroupStatement = statement.getPrincipalType() == PrincipalType.GROUP;
            if ((isUserStatement && statement.getPrincipalId().equals(userId))
                    || (isGroupStatement && isUserInGroup(userId, statement.getPrincipalId()))){
                if (statement.getActions().contains(action)) {
                    return;
                }
            }
        }
        throw new RuntimeException("You don't have access for " + action.name());
    }

    private boolean isUserInGroup(UUID userId, UUID groupId) {
        Optional<UUID> userIdsByGroupId = groupRepository.findUserIdsByGroupId(userId, groupId);
        System.out.println(userIdsByGroupId);
        return userIdsByGroupId.isPresent();
    }

    private boolean isBoardPublic(Board board) {
        UUID publicBoardGroupId = groupRepository
                .findByName("GROUP_ALL")
                .orElseThrow(() -> new RuntimeException("Cannot find this group"))
                .getId();
        return board
                .getAccessPolicy()
                .getStatements()
                .stream()
                .anyMatch(statement -> statement.getPrincipalType() == GROUP
                        && statement.getPrincipalId().equals(publicBoardGroupId));
    }
}
