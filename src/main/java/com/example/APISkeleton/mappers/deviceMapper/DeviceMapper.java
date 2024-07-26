package com.example.APISkeleton.mappers.deviceMapper;
import com.example.APISkeleton.persistance.entities.Device;
import com.example.APISkeleton.web.dtos.devices.response.GetDeviceResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceMapper {

    public static GetDeviceResponse deviceToGetDeviceResponse(Device device) {
        GetDeviceResponse.UserDTO userDTO = getUserDTO(device);
        GetDeviceResponse.PlantDTO plantDTO = getPlantDTO(device);
        List<GetDeviceResponse.PlantRecordDTO> plantRecordDTOs = getPlantRecordDTOs(device);

        return GetDeviceResponse.builder()
                .id(device.getId())
                .MAC(device.getMAC())
                .user(userDTO)
                .plant(plantDTO)
                .plantRecords(plantRecordDTOs)
                .build();
    }

    private static GetDeviceResponse.UserDTO getUserDTO(Device device) {
        return GetDeviceResponse.UserDTO.builder()
                .id(device.getUser().getId())
                .email(device.getUser().getEmail())
                .build();
    }

    private static GetDeviceResponse.PlantDTO getPlantDTO(Device device) {
        return GetDeviceResponse.PlantDTO.builder()
                .id(device.getPlant().getId())
                .name(device.getPlant().getName())
                .nameScientific(device.getPlant().getNameScientific())
                .humidityEnvironment(device.getPlant().getHumidityEnvironment())
                .humidityEnvironment(device.getPlant().getHumidityEnvironment())
                .brightness(device.getPlant().getBrightness())
                .ambientTemperature(device.getPlant().getAmbientTemperature())
                .mq135(device.getPlant().getMq135())
                .urlImagePlant(device.getPlant().getUrlImagePlant())
                .build();
    }

    private static List<GetDeviceResponse.PlantRecordDTO> getPlantRecordDTOs(Device device) {
        return device.getPlantRecords().stream()
                .map(record -> GetDeviceResponse.PlantRecordDTO.builder()
                        .id(record.getId())
                        .humidityEarth(record.getHumidityEarth())
                        .humidityEnvironment(record.getHumidityEnvironment())
                        .brightness(record.getBrightness())
                        .ambientTemperature(record.getAmbientTemperature())
                        .mq135(record.getMq135())
                        .estado(record.getEstado())
                        .createAt(record.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
