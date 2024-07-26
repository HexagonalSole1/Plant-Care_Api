package com.example.APISkeleton.web.dtos.devices.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeviceRequest {
    private String MAC;
    private String Correo;
    private String NamePlant;
}
