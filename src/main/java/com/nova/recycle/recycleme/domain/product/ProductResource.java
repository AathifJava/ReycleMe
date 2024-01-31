package com.nova.recycle.recycleme.domain.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductResource {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductDTO addProductDTO) {
        return productService.addProducts(addProductDTO);
    }

    @PostMapping("/uploadImage/{product_id}")
    public ResponseEntity<?> uploadProduct(@PathVariable("product_id") Long productId, @RequestBody MultipartFile[] file) {
        return productService.uploadProductImage(productId, file);
    }
}
