package com.example.APISkeleton.web.dtos.devices.response;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetDeviceResponse {
    private Long id;
    private String MAC;
    private UserDTO user;
    private PlantDTO plant;
    private List<PlantRecordDTO> plantRecords;

    @Data
    @Builder
    public static class UserDTO {
        private Long id;
        private String email;
    }

    @Data
    @Builder
    public static class PlantDTO {
        private Long id;
        private String name;
        private String nameScientific;
        private int humidityEarth;
        private int humidityEnvironment;
        private int brightness;
        private int ambientTemperature;
        private int mq135;
        private String urlImagePlant;
    }

    @Data
    @Builder
    public static class PlantRecordDTO {
        private Long id;
        private int humidityEarth;
        private int humidityEnvironment;
        private int brightness;
        private int ambientTemperature;
        private int mq135;
        private short estado;
        private LocalDateTime createAt;

    }
}