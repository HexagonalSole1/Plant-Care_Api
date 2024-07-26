package com.example.APISkeleton.web.dtos.plants.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PlantResponse {
    private Long id;
    private String name;
    private String nameScientific;
    private int humidityEarth;
    private int humidityEnvironment;
    private int brightness;
    private int ambientTemperature;
    private int mq135;
    private String urlImagePlant;
    private List<String> categories;
    private List<String> types;
    private List<String> families;
}
