package com.example.APISkeleton.web.controllers;

import com.example.APISkeleton.services.IS3service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("aws")
public class FileUploadController {

  private IS3service is3service;

  @Autowired
    public FileUploadController(IS3service is3service) {
        this.is3service = is3service;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
      return is3service.uploadResource(file);

    }


}
