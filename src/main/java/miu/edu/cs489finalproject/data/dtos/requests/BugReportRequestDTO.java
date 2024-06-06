package miu.edu.cs489finalproject.data.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugReportRequestDTO {
    private String title;
    private String description;
    private Long reporterId;
    private String status;
}
