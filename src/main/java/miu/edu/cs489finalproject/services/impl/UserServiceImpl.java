package miu.edu.cs489finalproject.services.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.UserResponseDTO;
import miu.edu.cs489finalproject.data.models.User;
import miu.edu.cs489finalproject.repositories.UserRepository;
import miu.edu.cs489finalproject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<UserResponseDTO> register(UserRequestDTO userRequestDTO) {
        User user = mapper.map(userRequestDTO, User.class);
        System.out.println(user);
        User savedUser = userRepository.save(user);
        UserResponseDTO userResponseDTO = mapper.map(savedUser, UserResponseDTO.class);
        return Optional.of(userResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> login(String username, String password) {
        Optional<User> user = userRepository.findUserByUsernameAndPassword(username, password);
        return user.map(value -> mapper.map(value, UserResponseDTO.class));
    }

    @Override
    public Optional<UserResponseDTO> updateUser(UserRequestDTO userRequestDTO, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User updatedUser = mapper.map(userRequestDTO, User.class);
            updatedUser.setId(userId);
            return Optional.of(mapper.map(userRepository.save(updatedUser), UserResponseDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void deleteUser(UserRequestDTO userRequestDTO) {
        userRepository.delete(mapper.map(userRequestDTO, User.class));
    }

    @Override
    public Optional<UserResponseDTO> getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> mapper.map(value, UserResponseDTO.class));
    }
}
