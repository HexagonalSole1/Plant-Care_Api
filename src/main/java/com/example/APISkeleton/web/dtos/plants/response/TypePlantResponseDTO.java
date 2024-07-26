package com.example.APISkeleton.web.dtos.plants.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TypePlantResponseDTO {
    private Long id;
    private String name;
}
