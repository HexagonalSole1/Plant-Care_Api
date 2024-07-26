package com.example.APISkeleton.services.impls;

import com.example.APISkeleton.mappers.plantMapper.PlantMapper;
import com.example.APISkeleton.persistance.entities.Device;
import com.example.APISkeleton.persistance.entities.PlantRecord;
import com.example.APISkeleton.persistance.repositories.IDeviceRepository;
import com.example.APISkeleton.persistance.repositories.IPlantRecordRepository;
import com.example.APISkeleton.security.SocketIoService;
import com.example.APISkeleton.services.IPlantRecordService;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRecordRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class PlantRecordImpl implements IPlantRecordService {
    private final SocketIoService socketIoService = new SocketIoService();

    private IPlantRecordRepository plantRecordRepository;
    private IDeviceRepository deviceRepository;

    private PlantMapper plantMapper;

    @Autowired
    public PlantRecordImpl(IPlantRecordRepository plantRecordRepository, IDeviceRepository deviceRepository, PlantMapper plantMapper) {
        this.plantRecordRepository = plantRecordRepository;
        this.deviceRepository = deviceRepository;
        this.plantMapper = plantMapper;
    }



    @PostConstruct
    public void init() {
        // Conectar al socket al iniciar la aplicación
        socketIoService.connect();
        if (socketIoService.isConnected()) {
            System.out.println("Conectado al socket correctamente al iniciar la aplicación.");
        } else {
            System.out.println("No se pudo conectar al socket al iniciar la aplicación.");
        }
    }


    @Override
    public BaseResponse createPlantRecord(CreatePlantRecordRequest request) {
        // Conectar a la sala de sockets
        socketIoService.connect();
        if (socketIoService.isConnected()) {
            System.out.println("Conectado al socket correctamente.");
        } else {
            System.out.println("No se pudo conectar al socket.");
        }
        // Obtener el dispositivo por MAC
        Device device = deviceRepository.findByMAC(request.getMAC())
                .orElseThrow(() -> new RuntimeException("Device not found"));

        // Crear un nuevo PlantRecord
        PlantRecord plantRecord = plantMapper.mapToPlantRecord(request, device);

        // Guardar el PlantRecord
        plantRecordRepository.save(plantRecord);

        if (socketIoService.isConnected()) {
            try {
                // Convertir el objeto CreatePlantRecordRequest a JSON manualmente
                String jsonRequest = convertRequestToJson(request);

                // Enviar el mensaje JSON a través del socket
                socketIoService.sendMessage(request.getMAC(), jsonRequest, request.getMAC());
            } catch (Exception e) {
                System.err.println("Error al enviar el mensaje: " + e.getMessage());
            }
        } else {
            System.out.println("No se pudo enviar el mensaje, el socket no está conectado.");
        }

        // Construir y devolver la respuesta
        return BaseResponse.builder()
                .data(null)
                .message("PlantRecord created successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private String convertRequestToJson(CreatePlantRecordRequest request) {
        // Construir el JSON manualmente
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"humidityEarth\":").append(request.getHumidityEarth()).append(",");
        jsonBuilder.append("\"humidityEnvironment\":").append(request.getHumidityEnvironment()).append(",");
        jsonBuilder.append("\"brightness\":").append(request.getBrightness()).append(",");
        jsonBuilder.append("\"ambientTemperature\":").append(request.getAmbientTemperature()).append(",");
        jsonBuilder.append("\"mq135\":").append(request.getMq135()).append(",");
        jsonBuilder.append("\"estado\":").append(request.getEstado()).append(",");
        jsonBuilder.append("\"MAC\":\"").append(request.getMAC()).append("\"");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }


}
