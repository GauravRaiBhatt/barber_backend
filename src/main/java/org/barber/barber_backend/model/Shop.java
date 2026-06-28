package org.barber.barber_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.barber.barber_backend.model.utils.OfferedService;
import org.barber.barber_backend.model.enums.Status;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shops")
public class Shop {

    @Id
    private String id;
    private String ownerId; // Link to the User who owns the shop
    private String name;
    private List<OfferedService> offeredServices;

    private String address; // Human-readable address string
    // For geospatial queries. Stores [longitude, latitude]
    private GeoJsonPoint location;

    private List<String> imageUrls;
    private List<String> videoUrls;

    @Builder.Default
    private Status approvalStatus = Status.PENDING;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
