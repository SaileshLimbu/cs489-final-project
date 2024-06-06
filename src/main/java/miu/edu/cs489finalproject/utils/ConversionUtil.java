package miu.edu.cs489finalproject.utils;

import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.UserResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.data.models.Comment;
import miu.edu.cs489finalproject.data.models.User;

import java.util.stream.Collectors;

public class ConversionUtil {

    public static BugReportResponseDTO toBugReportResponseDTO(BugReport bugReport) {
        BugReportResponseDTO dto = new BugReportResponseDTO();
        dto.setId(bugReport.getId());
        dto.setTitle(bugReport.getTitle());
        dto.setDescription(bugReport.getDescription());
        dto.setReporterDTO(toUserResponseDTO(bugReport.getReporter()));
        dto.setCommentsDTO(bugReport.getComments().stream()
                .map(ConversionUtil::toCommentResponseDTO)
                .collect(Collectors.toList()));
        dto.setStatus(bugReport.getStatus());
        return dto;
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public static UserRequestDTO toUserRequestDTO(User user) {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public static CommentResponseDTO toCommentResponseDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setUser(toUserResponseDTO(comment.getUser()));
        dto.setUpVote(comment.getUpVote());
        dto.setDownVote(comment.getDownVote());
        return dto;
    }

    public static BugReport toBugReport(BugReportRequestDTO dto, UserRequestDTO reporter) {
        BugReport bugReport = new BugReport();
        bugReport.setTitle(dto.getTitle());
        bugReport.setDescription(dto.getDescription());
        bugReport.setReporter(toUser(reporter));
        bugReport.setStatus(dto.getStatus());
        return bugReport;
    }

    public static Comment toComment(CommentRequestDTO dto, BugReportRequestDTO bugReportDTO, UserRequestDTO userDTO) {
        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setBugReport(toBugReport(bugReportDTO, userDTO));
        comment.setUser(toUser(userDTO));
        return comment;
    }

    public static User toUser(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
