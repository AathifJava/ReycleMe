package com.nova.recycle.recycleme.domain.security;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogInDTO  {
    @NotBlank(message = "Mobile is Required")
    @Pattern(regexp = "^07[01245678][0-9]{7}$")
    @Size(max = 10, min = 10)
    String mobile;
//    @NotBlank(message = "Password is Required")
//    String password;
}