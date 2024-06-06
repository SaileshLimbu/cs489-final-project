package miu.edu.cs489finalproject.repositories;

import miu.edu.cs489finalproject.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
