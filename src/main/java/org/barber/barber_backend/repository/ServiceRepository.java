package org.barber.barber_backend.repository;

import org.barber.barber_backend.model.ServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends MongoRepository<ServiceEntity, String> {
    List<ServiceEntity> findAllByIdIn(List<String> ids);
}