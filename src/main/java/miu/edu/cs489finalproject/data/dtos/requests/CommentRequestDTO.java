package miu.edu.cs489finalproject.data.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequestDTO {
    private String text;
    private Long bugReportId;
    private Long userId;
}
