package miu.edu.cs489finalproject.services;

import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CommentService {
    Optional<CommentResponseDTO> addComment(CommentRequestDTO commentRequestDTO);

    Optional<CommentResponseDTO> updateComment(CommentRequestDTO commentRequestDTO, Long id);

    Optional<CommentResponseDTO> deleteComment(Long id);

    Optional<CommentResponseDTO> getComment(Long id);

    Page<CommentResponseDTO> getAllComments(Long bugReportId, int pageSize, int pageNumber);

}
