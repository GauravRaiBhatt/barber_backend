package org.barber.barber_backend.dto;

import lombok.Data;
import org.barber.barber_backend.model.enums.Role;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
