package miu.edu.cs489finalproject.auth;


import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.models.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseWrapper<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = authenticationService.login(authenticationRequest);
        if (response != null) {
            return ResponseEntity.ok(new ResponseWrapper<>("success", "Authentication successful", response));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseWrapper<>("error", "Authentication failed", null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<AuthenticationResponse>> register(@RequestBody RegisterRequest registerRequest) {
        AuthenticationResponse response = authenticationService.register(registerRequest);
        if (response != null) {
            return ResponseEntity.ok(new ResponseWrapper<>("success", "User registered successfully", response));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("error", "Failed to register user", null));
        }
    }

}