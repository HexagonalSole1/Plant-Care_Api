package com.example.APISkeleton.services.impls;

import com.example.APISkeleton.mappers.IUserMapper;
import com.example.APISkeleton.persistance.entities.Device;
import com.example.APISkeleton.persistance.entities.User;
import com.example.APISkeleton.persistance.entities.pivots.UserRole;
import com.example.APISkeleton.persistance.repositories.IDeviceRepository;
import com.example.APISkeleton.persistance.repositories.IUserRepository;
import com.example.APISkeleton.services.IUserService;
import com.example.APISkeleton.web.dtos.requests.CreateUserRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import com.example.APISkeleton.web.dtos.user.response.UserResponseDto;
import com.example.APISkeleton.web.exceptions.ConflictException;
import com.example.APISkeleton.web.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;



    private final IDeviceRepository deviceRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final IUserMapper userMapper;

    public UserServiceImpl(IUserRepository repository, IDeviceRepository deviceRepository, IUserMapper userMapper) {
        this.repository = repository;
        this.deviceRepository = deviceRepository;
        this.userMapper = userMapper;
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {

        System.out.println(request.getEmail());
        Optional<User> optionalUser = getOptionalUserByEmail(request.getEmail());

        if (optionalUser.isPresent()) {
            throw new ConflictException("User already exists with this email");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPassword(encodedPassword);
        user.setGender(request.getGender());

        repository.save(user);

        return BaseResponse.builder()
                .data(userMapper.toCreateUserResponse(user))
                .message("User created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }
    @Override
    public BaseResponse getUserByEmailAdrian(String email) {
        Optional<User> optionalUser = getOptionalUserByEmail(email);


        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return BaseResponse.builder()
                    .data(convertToDto(user))
                    .message("User found successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
            return BaseResponse.builder()
                    .data(null)
                    .message("User not found")
                    .success(false)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();

    }


    @Override
    public User getByEmail(String email) {
        return repository.findByEmailNative(email).orElseThrow(() -> new ResourceNotFoundException(User.class));
    }


    public BaseResponse getUsers() {
        List<User> listUser = repository.findAll();
        List<UserResponseDto> userResponseDtos = listUser.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(userResponseDtos)
                .message("Users found")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private UserResponseDto convertToDto(User user) {
        List<UserResponseDto.UserRoleDto> userRoleDtos = user.getUserRoles().stream()
                .map(this::convertUserRoleToDto)
                .collect(Collectors.toList());

        Optional<List<Device>> optionalDevice = deviceRepository.findByUser(user);
        if(optionalDevice.isPresent())
        {
            List<Device> devices= optionalDevice.get();

            List<String> namePlants = devices.stream()
                    .map(device -> device.getPlant().getName())
                    .collect(Collectors.toList());

            return UserResponseDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .namePlant(namePlants)
                    .createdAt(user.getCreatedAt())
                    .userRoles(userRoleDtos)
                    .build();
        }


        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .namePlant(null)
                .createdAt(user.getCreatedAt())
                .userRoles(userRoleDtos)
                .build();
    }

    private UserResponseDto.UserRoleDto convertUserRoleToDto(UserRole userRole) {
        return UserResponseDto.UserRoleDto.builder()
                .id(userRole.getId())
                .role(UserResponseDto.UserRoleDto.RoleDto.builder()
                        .id(userRole.getRole().getId())
                        .name(userRole.getRole().getName())
                        .build())
                .build();
    }

    @Override
    public Optional<User> getOptionalUserByEmail(String email) {
        return repository.findByEmailNative(email);
    }

}
