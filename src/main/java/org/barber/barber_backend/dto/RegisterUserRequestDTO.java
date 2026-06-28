package org.barber.barber_backend.dto;

import lombok.Data;
import org.barber.barber_backend.model.enums.Role;

@Data
public class RegisterUserRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
