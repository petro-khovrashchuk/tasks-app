package com.petrokhovrashchuk.jobinterview.dto;

public record TaskDto(
    Long id,
    String title,
    String description,
    Boolean completed
) {

}
