package miu.edu.cs489finalproject.controllers;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(@RequestBody CommentRequestDTO commentRequestDTO, @PathVariable("id") Long commentId) {
        Optional<CommentResponseDTO> commentResponseDTO = commentService.updateComment(commentRequestDTO, commentId);
        return commentResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> deleteComment(@PathVariable("id") Long commentId) {
        Optional<CommentResponseDTO> deletedComment = commentService.deleteComment(commentId);
        if (deletedComment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }
}
