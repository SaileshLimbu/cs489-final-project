package miu.edu.cs489finalproject;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.UserResponseDTO;
import miu.edu.cs489finalproject.services.BugReportService;
import miu.edu.cs489finalproject.services.CommentService;
import miu.edu.cs489finalproject.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class Cs489FinalProjectApplication implements CommandLineRunner {

    private final UserService userService;
    private final BugReportService bugReportService;
    private final CommentService commentService;

    public static void main(String[] args) {
        SpringApplication.run(Cs489FinalProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserRequestDTO userRequestDTO1 = new UserRequestDTO("test1", "test", "test1@gmail.com", "test1", "test");
        UserRequestDTO userRequestDTO2 = new UserRequestDTO("test1", "test", "test2@gmail.com", "test2", "test");

        Optional<UserResponseDTO> registeredUser1 = userService.register(userRequestDTO1);
        registeredUser1.ifPresent(userResponseDTO -> System.out.println("Registered user: " + userResponseDTO.getUsername()));

        Optional<UserResponseDTO> registeredUser2 = userService.register(userRequestDTO2);
        registeredUser2.ifPresent(userResponseDTO -> System.out.println("Registered user: " + userResponseDTO.getUsername()));
    }
}
