package org.barber.barber_backend.model.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferedService {
    private String serviceId; // Reference to the global Service document
    private Double price;
    private Integer durationInMinutes;
    private String description;
    private List<String> imageUrls;
    private List<String> videoUrls;
}
