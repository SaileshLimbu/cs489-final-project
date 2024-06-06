package miu.edu.cs489finalproject.data.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDTO {
    private Long id;
    private String text;
    private UserResponseDTO user;
    private int upVote;
    private int downVote;
}
