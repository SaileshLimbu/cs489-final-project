package miu.edu.cs489finalproject.controllers;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.services.CommentService;
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

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        Optional<CommentResponseDTO> commentResponseDTO = commentService.addComment(commentRequestDTO);
        return commentResponseDTO.map(responseDTO -> new ResponseEntity<>(responseDTO, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/bug-report")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(@RequestParam Long id, @RequestParam int pageSize, @RequestParam int pageNumber) {
        Page<CommentResponseDTO> commentResponseDTOPage = commentService.getAllComments(id, pageSize, pageNumber);
        return new ResponseEntity<>(commentResponseDTOPage.getContent(), HttpStatus.OK);
    }

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
