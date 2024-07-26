package com.example.APISkeleton.services;

import org.springframework.web.multipart.MultipartFile;

public interface IS3service {

    String uploadResource(MultipartFile multipartFile);
}
