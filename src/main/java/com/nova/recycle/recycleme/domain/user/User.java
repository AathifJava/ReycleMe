package com.nova.recycle.recycleme.domain.user;

import com.nova.recycle.recycleme.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column(name = "mobile", length = 10, unique = true)
    private String mobile;
    @Column(name = "password", length = 20)
    private String password;
    @Column(name = "verification_code", length = 10)
    private String verificationCode;
    @Column(name = "profile_image_path", columnDefinition = "TEXT")
    private String profileImagePath;
    @Column(name = "status")
    private int status;
}
