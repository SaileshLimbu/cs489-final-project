package miu.edu.cs489finalproject;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.UserResponseDTO;
import miu.edu.cs489finalproject.data.models.Role;
import miu.edu.cs489finalproject.services.BugReportService;
import miu.edu.cs489finalproject.services.CommentService;
import miu.edu.cs489finalproject.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Cs489FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cs489FinalProjectApplication.class, args);
    }

}
