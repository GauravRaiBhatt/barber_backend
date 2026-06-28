package org.barber.barber_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingRequestDTO {
    private String userId;
    private String shopId;
    private LocalDateTime appointmentDateTime;
    private List<String> serviceIds; // The IDs of the OfferedServices from the Shop
}
