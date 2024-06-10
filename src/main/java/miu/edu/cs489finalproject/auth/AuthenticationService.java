package miu.edu.cs489finalproject.auth;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.config.JwtService;
import miu.edu.cs489finalproject.data.models.User;
import miu.edu.cs489finalproject.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = new User(registerRequest.getUsername(),
                encodedPassword,
                registerRequest.getEmail(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getRole(),
                registerRequest.isBlocked());
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        var user = userRepository.findUserByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException(authenticationRequest.getUsername()));
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
