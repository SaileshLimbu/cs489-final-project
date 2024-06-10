package miu.edu.cs489finalproject.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import miu.edu.cs489finalproject.data.models.Role;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean isBlocked;
}
