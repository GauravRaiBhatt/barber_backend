package org.barber.barber_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shops")
public class Shop {
    @Id
    private String id;
    private String ownerId;
    private String name;
    private String address;
    @Builder.Default
    private String approvalStatus = "APPROVED";
}