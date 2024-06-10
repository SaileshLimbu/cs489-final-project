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
//        UserRequestDTO userRequestDTO1 = new UserRequestDTO("test1", "test", "test1@gmail.com", "test1", "test", Role.ADMIN, false);
//        UserRequestDTO userRequestDTO2 = new UserRequestDTO("test2", "test", "test2@gmail.com", "test2", "test", Role.MEMBER, false);
//
//        Optional<UserResponseDTO> registeredUser1 = userService.register(userRequestDTO1);
//        registeredUser1.ifPresent(userResponseDTO -> System.out.println("Registered user: " + userResponseDTO.getUsername()));
//
//        Optional<UserResponseDTO> registeredUser2 = userService.register(userRequestDTO2);
//        registeredUser2.ifPresent(userResponseDTO -> System.out.println("Registered user: " + userResponseDTO.getUsername()));
//
//        BugReportRequestDTO bugReportRequestDTO = new BugReportRequestDTO("This is my First Bug.", "Having some issue with the mysql server not starting.", registeredUser1.get().getId(), "OPEN");
//        Optional<BugReportResponseDTO> bugReportResponseDTO = bugReportService.addBugReport(bugReportRequestDTO);
//        bugReportResponseDTO.ifPresent(System.out::println);
//
//        CommentRequestDTO commentRequestDTO = new CommentRequestDTO("I am also getting the same error", bugReportResponseDTO.get().getId(), registeredUser2.get().getId());
//        Optional<CommentResponseDTO> commentResponseDTO = commentService.addComment(commentRequestDTO);
//        commentResponseDTO.ifPresent(System.out::println);
//
//        Optional<BugReportResponseDTO> bugReportResponseDTO2 = bugReportService.getBugReport(bugReportResponseDTO.get().getId());
//        bugReportResponseDTO2.ifPresent(System.out::println);
//
//        Page<CommentResponseDTO> commentResponseDTOPage = commentService.getAllComments(bugReportResponseDTO.get().getId(), 5, 0);
//        commentResponseDTOPage.forEach(System.out::println);

    }
}
