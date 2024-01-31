package com.nova.recycle.recycleme.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> userSignIn(@RequestBody UserLogInDTO userLogInDTO){
        return authService.userSignIn(userLogInDTO);
    }

    @PostMapping("/verifi")
    public ResponseEntity<?> verifiUser(@RequestBody VerificationDTO verificationDTO){
        return authService.verifiUser(verificationDTO);
    }

}