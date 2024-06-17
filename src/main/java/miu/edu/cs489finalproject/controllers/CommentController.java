package miu.edu.cs489finalproject.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.config.JwtService;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.models.ResponseWrapper;
import miu.edu.cs489finalproject.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<CommentResponseDTO>> createComment(@RequestBody CommentRequestDTO commentRequestDTO, HttpServletRequest request) {
        Long userId = jwtService.extractUserId(request);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ResponseWrapper<>("error", "Unauthorized", null));
        }
        commentRequestDTO.setUserId(userId);
        Optional<CommentResponseDTO> commentResponseDTO = commentService.addComment(commentRequestDTO);
        if (commentResponseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>("success", "Comment created successfully", commentResponseDTO.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>("error", "Failed to create comment", null));
        }
    }

    @GetMapping("/bug-report")
    public ResponseEntity<ResponseWrapper<List<CommentResponseDTO>>> getAllComments(@RequestParam Long id, @RequestParam int pageSize, @RequestParam int pageNumber) {
        Page<CommentResponseDTO> commentResponseDTOPage = commentService.getAllComments(id, pageSize, pageNumber);
        return ResponseEntity.ok(new ResponseWrapper<>("success", "Comments fetched successfully", commentResponseDTOPage.getContent()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<CommentResponseDTO>> updateComment(@RequestBody CommentRequestDTO commentRequestDTO, @PathVariable("id") Long commentId, HttpServletRequest request) {
        Long userId = jwtService.extractUserId(request);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ResponseWrapper<>("error", "Unauthorized", null));
        }
        Optional<CommentResponseDTO> commentToUpdate = commentService.getCommentByUser(commentId, userId);
        if (commentToUpdate.isPresent()) {
            modelMapper.map(commentRequestDTO, commentToUpdate.get());
            commentRequestDTO.setUserId(userId);
            Optional<CommentResponseDTO> response = commentService.updateComment(commentRequestDTO, commentId);
            if (response.isPresent()) {
                return ResponseEntity.ok(new ResponseWrapper<>("success", "Comment updated successfully", response.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>("error", "Failed to update comment", null));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>("error", "Comment not found or User not authorized", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteComment(@PathVariable("id") Long commentId, HttpServletRequest request) {
        Long userId = jwtService.extractUserId(request);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ResponseWrapper<>("error", "Unauthorized", null));
        }
        Optional<CommentResponseDTO> commentToDelete = commentService.getCommentByUser(commentId, userId);
        if (commentToDelete.isPresent()) {
            commentService.deleteComment(commentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseWrapper<>("success", "Comment deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>("error", "Comment not found or User not authorized", null));
    }
}
