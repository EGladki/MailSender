package com.gladkiei.mailsender.dtos;

import com.gladkiei.mailsender.enums.TaskStatus;

import java.sql.Timestamp;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        String createdAt,
        Timestamp completedAt,
        TaskStatus status,
        Long userId
) {

}
