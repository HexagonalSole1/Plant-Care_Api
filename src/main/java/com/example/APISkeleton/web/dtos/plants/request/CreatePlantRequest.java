package com.example.APISkeleton.web.dtos.plants.request;


import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlantRequest  {
    private String name;
    private String nameScientific;
    private Optional<Integer> humidityEarth = Optional.empty();
    private Optional<Integer> humidityEnvironment = Optional.empty();
    private Optional<Integer> brightness = Optional.empty();
    private Optional<Integer> ambientTemperature = Optional.empty();
    private Optional<Integer> mq135 = Optional.empty();
    private String urlImagePlant;


    private List<String> categories;
    private List<String> types;
    private List<String> families;
}
