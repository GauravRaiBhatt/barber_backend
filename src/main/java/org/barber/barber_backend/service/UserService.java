package org.barber.barber_backend.service;

import org.barber.barber_backend.model.User;
import org.barber.barber_backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(ObjectId id){
        return userRepository.findById(id);
    }
}
