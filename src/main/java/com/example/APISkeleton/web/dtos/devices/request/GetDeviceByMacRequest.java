package com.example.APISkeleton.web.dtos.devices.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDeviceByMacRequest {
    private String MAC;
}
