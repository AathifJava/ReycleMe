package com.nova.recycle.recycleme.domain.security;

import com.nova.recycle.recycleme.domain.security.util.JwtUtil;
import com.nova.recycle.recycleme.domain.user.User;
import com.nova.recycle.recycleme.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<?> userSignIn(UserLogInDTO userLogInDTO) {
        if (userLogInDTO.getMobile().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile Not Found");
        } else {
            Optional<User> optionalUser = userRepository.findByMobile(userLogInDTO.getMobile());

            if (optionalUser.isEmpty()) {
                User user = new User();
                user.setMobile(userLogInDTO.getMobile());
                String verificationCode = String.format("%06d", new Random().nextInt(999999));
                user.setVerificationCode(verificationCode);
                user.setStatus(2);
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(verificationCode);
            } else {
                User user = optionalUser.get();
                String verificationCode = String.format("%06d", new Random().nextInt(999999));
                user.setVerificationCode(verificationCode);
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(verificationCode);
            }

        }
    }


    public ResponseEntity<?> verifiUser(VerificationDTO verificationDTO) {
        if (verificationDTO.getMobile().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile Not Found");
        } else if (verificationDTO.getVerificationCode().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Verification Code Not Found");
        }else {
            Optional<User> optionalUser = userRepository.findByMobile(verificationDTO.getMobile());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Mobile Number Not Found. Try Again!");
            } else {
                User user = optionalUser.get();
                if (!user.getVerificationCode().equals(verificationDTO.getVerificationCode())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entered OTP is Wrong. Try Again!");
                }
                user.setStatus(1);
                userRepository.save(user);
                String assessToken = jwtUtil.generateAccessToken(user);
                Map<String, String> data = new HashMap<>();
                data.put("message", "success");
                data.put("accessToken", assessToken);

                return ResponseEntity.status(HttpStatus.OK).body(data);

            }

        }
    }
}
