package miu.edu.cs489finalproject.data.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugReportResponseDTO {
    private Long id;
    private String title;
    private String description;
    private UserResponseDTO reporterDTO;
    private List<CommentResponseDTO> commentsDTO;
    private String status;
}
