package com.nova.recycle.recycleme.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationDTO {
    private String mobile;
    private String verificationCode;
}
