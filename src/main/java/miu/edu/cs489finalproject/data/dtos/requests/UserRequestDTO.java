package miu.edu.cs489finalproject.data.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.cs489finalproject.data.models.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean isBlocked;
}
