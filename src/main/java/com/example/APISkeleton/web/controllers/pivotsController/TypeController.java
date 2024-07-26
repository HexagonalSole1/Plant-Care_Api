package com.example.APISkeleton.web.controllers.pivotsController;

import com.example.APISkeleton.services.ITypePlantService;
import com.example.APISkeleton.web.dtos.category.request.CreateFamilyRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/typePlant")
public class TypeController {

    private ITypePlantService service;

    @Autowired
    public TypeController(ITypePlantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateFamilyRequest request) {
        BaseResponse baseResponse = service.create(request);
        return baseResponse.buildResponseEntity();
    }
    @GetMapping
    public ResponseEntity<BaseResponse> get() {
        BaseResponse baseResponse = service.get();
        return baseResponse.buildResponseEntity();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        BaseResponse baseResponse = service.delete(id);
        return baseResponse.buildResponseEntity();
    }

}
