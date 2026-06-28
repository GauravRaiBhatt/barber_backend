package org.barber.barber_backend.dto;

import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    private String name;
    private String phone;
}
