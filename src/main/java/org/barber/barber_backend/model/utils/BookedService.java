package org.barber.barber_backend.model.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookedService {
    private String serviceId;
//    private String name; // Capture the name for easy display
//    private Double price; // Capture the price at time of booking
//    private Integer durationInMinutes; // Capture the duration at time of booking
}
