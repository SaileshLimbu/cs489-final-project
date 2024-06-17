package miu.edu.cs489finalproject.services.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.data.models.Comment;
import miu.edu.cs489finalproject.data.models.User;
import miu.edu.cs489finalproject.repositories.BugReportRepository;
import miu.edu.cs489finalproject.repositories.CommentRepository;
import miu.edu.cs489finalproject.repositories.UserRepository;
import miu.edu.cs489finalproject.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BugReportRepository bugReportRepository;
    private final ModelMapper mapper;
    private final ModelMapper modelMapper;

    @Override
    public Optional<CommentResponseDTO> addComment(CommentRequestDTO commentRequestDTO) {
        User user = userRepository.findById(commentRequestDTO.getUserId()).get();
        BugReport bugreport = bugReportRepository.findById(commentRequestDTO.getBugReportId()).get();
        Comment comment = mapper.map(commentRequestDTO, Comment.class);
        comment.setUser(user);
        comment.setBugReport(bugreport);
        return Optional.of(mapper.map(commentRepository.save(comment), CommentResponseDTO.class));
    }

    @Override
    public Optional<CommentResponseDTO> updateComment(CommentRequestDTO commentRequestDTO, Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment updatedComment = mapper.map(commentRequestDTO, Comment.class);
            updatedComment.setId(id);
            return Optional.of(mapper.map(commentRepository.save(updatedComment), CommentResponseDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            commentRepository.deleteById(id);
        }
    }

    @Override
    public Optional<CommentResponseDTO> getComment(Long id) {
        return Optional.of(mapper.map(commentRepository.findById(id), CommentResponseDTO.class));
    }

    @Override
    public Optional<CommentResponseDTO> getCommentByUser(Long commentId, Long userId) {
        return Optional.of(mapper.map(commentRepository.findCommentByUserId(commentId, userId), CommentResponseDTO.class));
    }

    @Override
    public Page<CommentResponseDTO> getAllComments(Long bugReportId, int pageSize, int pageNumber) {
        BugReport bugReport = bugReportRepository.findById(bugReportId).get();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Comment> comments = commentRepository.findAllByBugReport(bugReport, pageRequest);
        List<CommentResponseDTO> commentResponseDTOList = comments.stream().map(comment -> mapper.map(comment, CommentResponseDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(commentResponseDTOList, pageRequest, commentResponseDTOList.size());
    }
}
