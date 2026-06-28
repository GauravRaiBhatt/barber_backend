package org.barber.barber_backend.service;

import org.barber.barber_backend.dto.RegisterUserRequestDTO;
import org.barber.barber_backend.dto.UpdateUserRequestDTO;
import org.barber.barber_backend.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(String userId);
    UserResponseDTO updateUser(String userId, UpdateUserRequestDTO updateUserRequestDTO);
    void deleteUser(String userId);
}
