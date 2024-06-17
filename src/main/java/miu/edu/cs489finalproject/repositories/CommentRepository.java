package miu.edu.cs489finalproject.repositories;

import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.data.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByBugReport(BugReport bugReport, Pageable pageable);
    @Query(value = "SELECT * FROM comments where id=:commentId and user_id=:userId", nativeQuery = true)
    Optional<Comment> findCommentByUserId(Long commentId, Long userId);
}
