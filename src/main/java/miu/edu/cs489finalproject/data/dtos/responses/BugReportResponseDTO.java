package miu.edu.cs489finalproject.data.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugReportResponseDTO {
    private Long id;
    private String title;
    private String description;
    private UserResponseDTO reporterDTO;
    private String status;
}
