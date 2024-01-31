package com.nova.recycle.recycleme.domain.user;

import com.nova.recycle.recycleme.domain.security.UserLogInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;

    @PostMapping("/userProfile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserDTO userDTO){
        return userService.updateUserProfile(userDTO);
    }

    @PostMapping("/profile-image")
    public ResponseEntity<?> userUpdateProfileImage(@RequestBody MultipartFile file) throws IOException {
        return userService.userUpdateProfileImage(file);
    }

}