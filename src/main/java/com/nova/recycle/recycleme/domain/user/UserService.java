package com.nova.recycle.recycleme.domain.user;

import com.nova.recycle.recycleme.domain.security.RequestMetaDTO;
import com.nova.recycle.recycleme.domain.security.UserLogInDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String UPLOAD_DIR = "upload";

    @Autowired
    RequestMetaDTO requestMetaDTO;

    public ResponseEntity<?> updateUserProfile(UserDTO userDTO) {
        if (!userRepository.findByMobile(requestMetaDTO.getMobile()).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Invalid Mobile");
        } else {

            User user = userRepository.findByMobile(requestMetaDTO.getMobile()).get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Profile Updated");
        }
    }

    public ResponseEntity<?> userUpdateProfileImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Upload Profile Image");
        } else {
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = System.currentTimeMillis() + "." + extension;

            Path filePath = path.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String appUrl = String.format("http://%s:%s", InetAddress.getLocalHost(), 8080);
            String url = UPLOAD_DIR + "/" + fileName;

            User user = userRepository.findByMobile(requestMetaDTO.getMobile()).get();
            user.setProfileImagePath(url);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("Profile Image Upload Successfully");


//            try {
//                String projectRoot = System.getProperty("user.dir");
//
//                String originalFilename = file.getOriginalFilename();
//                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
//                String newFileName = UUID.randomUUID() + fileExtension;
//
//                String imagePath = "/uploads/" + newFileName;
//                Path path = Paths.get(projectRoot + imagePath);
//                File saveFile = new File(String.valueOf(path));
//                file.transferTo(saveFile);
//
//                User user = userRepository.findByMobile(requestMetaDTO.getMobile()).get();
//                user.setProfileImagePath(imagePath);
//                userRepository.save(user);
//
//                return ResponseEntity.status(HttpStatus.OK).body("Profile Image Upload Successfully");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}
