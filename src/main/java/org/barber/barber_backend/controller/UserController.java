package org.barber.barber_backend.controller;

import lombok.RequiredArgsConstructor;
import org.barber.barber_backend.model.User;
import org.barber.barber_backend.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable ObjectId id){
        return userService.getUserById(id).orElse(null);
    }
}
