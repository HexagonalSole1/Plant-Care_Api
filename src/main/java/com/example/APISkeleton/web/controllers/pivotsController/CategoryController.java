package com.example.APISkeleton.web.controllers.pivotsController;

import com.example.APISkeleton.services.ICategoryService;
import com.example.APISkeleton.web.dtos.category.request.CreateCategoryRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateCategoryRequest request) {
        BaseResponse baseResponse = categoryService.create(request);
        return baseResponse.buildResponseEntity();
    }
    @GetMapping
    public ResponseEntity<BaseResponse> get() {
        BaseResponse baseResponse = categoryService.get();
        return baseResponse.buildResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        BaseResponse baseResponse = categoryService.delete(id);
        return baseResponse.buildResponseEntity();
    }
}
