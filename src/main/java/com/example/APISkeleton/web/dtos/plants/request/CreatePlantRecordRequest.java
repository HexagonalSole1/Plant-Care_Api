package com.example.APISkeleton.web.dtos.plants.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlantRecordRequest {

    @NotNull
    private int humidityEarth;

    @NotNull
    private int humidityEnvironment;

    @NotNull
    private int brightness;

    @NotNull
    private int ambientTemperature;

    @NotNull
    private int mq135;

    @NotNull
    private short estado;

    @NotNull
    private String MAC;
}
