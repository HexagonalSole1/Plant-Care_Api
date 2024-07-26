package com.example.APISkeleton.services.impls;

import com.example.APISkeleton.mappers.deviceMapper.DeviceMapper;
import com.example.APISkeleton.persistance.entities.Device;
import com.example.APISkeleton.persistance.entities.Plant;
import com.example.APISkeleton.persistance.entities.User;
import com.example.APISkeleton.persistance.repositories.IDeviceRepository;
import com.example.APISkeleton.persistance.repositories.IPlantRepository;
import com.example.APISkeleton.persistance.repositories.IUserRepository;
import com.example.APISkeleton.services.IDeviceService;
import com.example.APISkeleton.web.dtos.devices.request.CreateDeviceRequest;
import com.example.APISkeleton.web.dtos.devices.response.GetDeviceResponse;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DeviceServiceImpl implements IDeviceService {

    IDeviceRepository deviceRepository;
    IUserRepository userRepository;
    IPlantRepository plantRepository;

    @Autowired
    public DeviceServiceImpl(IDeviceRepository deviceRepository, IUserRepository userRepository, IPlantRepository plantRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
    }
    @Override
    public BaseResponse createDevice(CreateDeviceRequest request) {

        Optional<User> optionalUser = userRepository.findByEmailNative(request.getCorreo());
        Optional<Plant> optionalPlant = plantRepository.findByName(request.getNamePlant());

        if (optionalUser.isPresent()) {

            if (optionalPlant.isPresent()) {
                Device device = Device.builder()
                        .MAC(request.getMAC())
                        .plant(optionalPlant.get())
                        .user(optionalUser.get())
                        .build();
                deviceRepository.save(device);
                return BaseResponse.builder()
                        .data(null)
                        .message("Device added successfully")
                        .success(true)
                        .httpStatus(HttpStatus.OK)
                        .build();

            }

        }


        return BaseResponse.builder()
                .data(null)
                .message("Device not added unsuccessfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getDeviceByMAC(String macRequest) {
        Optional<Device> optionalDevice = deviceRepository.findByMAC(macRequest);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            GetDeviceResponse response = DeviceMapper.deviceToGetDeviceResponse(device);

            return BaseResponse.<GetDeviceResponse>builder()
                    .data(response)
                    .message("Device retrieved successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.<GetDeviceResponse>builder()
                    .data(null)
                    .message("Device not found")
                    .success(false)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    public BaseResponse getDeviceByEmail(String emailRequest) {
        Optional<User> optionalUser = userRepository.findByEmailNative(emailRequest);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<List<Device>> optionalDevices = deviceRepository.findByUser(user);

            if (optionalDevices.isPresent() && !optionalDevices.get().isEmpty()) {
                List<Device> devices = optionalDevices.get();
                List<GetDeviceResponse> responses = devices.stream()
                        .map(DeviceMapper::deviceToGetDeviceResponse) // Utiliza el mapper para convertir cada Device a GetDeviceResponse
                        .collect(Collectors.toList());

                return BaseResponse.<GetDeviceResponse>builder()
                        .data(responses)
                        .message("Devices retrieved successfully")
                        .success(true)
                        .httpStatus(HttpStatus.OK)
                        .build();
            } else {
                return BaseResponse.<GetDeviceResponse>builder()
                        .data(null)
                        .message("No devices found for the user")
                        .success(false)
                        .httpStatus(HttpStatus.NO_CONTENT)
                        .build();
            }
        } else {
            return BaseResponse.<GetDeviceResponse>builder()
                    .data(null)
                    .message("User not found")
                    .success(false)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }


    @Override
    public BaseResponse deleteDeviceByMAC(String macRequest) {
        Optional<Device> optionalDevice = deviceRepository.findByMAC(macRequest);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();

            deviceRepository.delete(device);
            return BaseResponse.<GetDeviceResponse>builder()
                    .data(null)
                    .message("Device deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.<GetDeviceResponse>builder()
                    .data(null)
                    .message("Device not found")
                    .success(false)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }


    @Override
    public BaseResponse getDevice() {
        List<Device> devices = (List<Device>) deviceRepository.findAll();

        List<GetDeviceResponse> responses = devices.stream()
                .map(DeviceMapper::deviceToGetDeviceResponse) // Utiliza el mapper para convertir cada Device a GetDeviceResponse
                .collect(Collectors.toList());

        return BaseResponse.<List<GetDeviceResponse>>builder()
                .data(responses)
                .message("Devices retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }


}
