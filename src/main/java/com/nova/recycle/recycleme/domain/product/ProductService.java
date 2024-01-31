package com.nova.recycle.recycleme.domain.product;

import com.nova.recycle.recycleme.domain.product.category.CategoryRepository;
import com.nova.recycle.recycleme.domain.product.time.TimeRepository;
import com.nova.recycle.recycleme.domain.security.RequestMetaDTO;
import com.nova.recycle.recycleme.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TimeRepository timeRepository;
    private final PathRepository pathRepository;

    @Autowired
    RequestMetaDTO requestMetaDTO;

    public ResponseEntity<?> addProducts(AddProductDTO addProductDTO) {
        if (!userRepository.findByMobile(requestMetaDTO.getMobile()).isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        } else if (!categoryRepository.findById(addProductDTO.getCategoryId()).isPresent()) {
            return ResponseEntity.badRequest().body("Category not found");
        } else if (!timeRepository.findById(addProductDTO.getTimeId()).isPresent()) {
            return ResponseEntity.badRequest().body("Time not found");
        } else {

            Product product = new Product();
            product.setUserId(requestMetaDTO.getId());
            product.setCategoryId(addProductDTO.getCategoryId());
            product.setTimeId(addProductDTO.getTimeId());
            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.OK).body("Product Add Succssfully ");
        }
    }


    public ResponseEntity<?> uploadProductImage(Long productId, MultipartFile[] file) {
        if (file.length > 0) {
            for (MultipartFile multipartFile : file) {
                try {
                    String projectRoot = System.getProperty("user.dir");
                    String originalFilename = multipartFile.getOriginalFilename();
                    if (originalFilename != null) {
                        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                        String newFileName = UUID.randomUUID() + fileExtension;
                        String imagePath = "/uploads/" + newFileName;
                        Path path = Paths.get(projectRoot + imagePath);
                        File saveFile = new File(String.valueOf(path));
                        multipartFile.transferTo(saveFile);
                        Optional<Product> optionalProduct = productRepository.findById(productId);
                        if (optionalProduct.isPresent()) {
                            ImagePath image = new ImagePath();
                            image.setName(imagePath);
                            image.setProductId(productId);
                            pathRepository.save(image);
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Id Not Found");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File Name Not Found");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
             return ResponseEntity.status(HttpStatus.CREATED).body("Image Upload Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File Not Found");
        }
    }

}
