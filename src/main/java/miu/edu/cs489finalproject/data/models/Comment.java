package miu.edu.cs489finalproject.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "comments")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "bug_report_id", nullable = false)
    private BugReport bugReport;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
