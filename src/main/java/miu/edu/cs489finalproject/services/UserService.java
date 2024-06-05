package miu.edu.cs489finalproject.services;

import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.UserResponseDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserResponseDTO> register(UserRequestDTO userRequestDTO);
    Optional<UserResponseDTO> login(String username, String password);
    Optional<UserResponseDTO> updateUser(UserRequestDTO userRequestDTO);
    void deleteUser(UserRequestDTO userRequestDTO);
    Optional<UserResponseDTO> getUser(Long userId);
}
