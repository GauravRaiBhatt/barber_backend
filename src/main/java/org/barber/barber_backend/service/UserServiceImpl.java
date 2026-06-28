package org.barber.barber_backend.service;

import lombok.RequiredArgsConstructor;
import org.barber.barber_backend.dto.RegisterUserRequestDTO;
import org.barber.barber_backend.dto.UpdateUserRequestDTO;
import org.barber.barber_backend.dto.UserResponseDTO;
import org.barber.barber_backend.model.User;
import org.barber.barber_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO registerUser(RegisterUserRequestDTO registerDto) {
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword())); // Hashing the password
        user.setRole(registerDto.getRole());

        User savedUser = userRepository.save(user);
        return mapUserToUserResponseDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapUserToUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return mapUserToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(String userId, UpdateUserRequestDTO updateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        user.setName(updateDto.getName());
        user.setPhone(updateDto.getPhone());

        User updatedUser = userRepository.save(user);
        return mapUserToUserResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    // Manual Mapper
    private UserResponseDTO mapUserToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
