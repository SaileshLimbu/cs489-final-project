package miu.edu.cs489finalproject.services.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.repositories.CommentRepository;
import miu.edu.cs489finalproject.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public Optional<CommentResponseDTO> addComment(CommentRequestDTO commentRequestDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<CommentResponseDTO> updateComment(CommentRequestDTO commentRequestDTO, Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteComment(Long id) {

    }

    @Override
    public Optional<CommentResponseDTO> getComment(Long id) {
        return Optional.empty();
    }
}
