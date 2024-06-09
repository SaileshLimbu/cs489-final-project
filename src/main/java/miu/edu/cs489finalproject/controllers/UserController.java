package miu.edu.cs489finalproject.controllers;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.UserResponseDTO;
import miu.edu.cs489finalproject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO user) {
        Optional<UserResponseDTO> userResponseDTO = userService.login(user.getUsername(), user.getPassword());
        if (userResponseDTO.isPresent()) {
            UserResponseDTO userResponse = userResponseDTO.get();
            return ResponseEntity.ok(userResponse);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO user) {
        Optional<UserResponseDTO> userResponseDTO = userService.register(user);
        if (userResponseDTO.isPresent()) {
            UserResponseDTO userResponse = userResponseDTO.get();
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.ok().build();
    }
}
