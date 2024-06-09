package miu.edu.cs489finalproject.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "bug_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User reporter;
    @OneToMany(mappedBy = "bugReport", cascade = CascadeType.ALL)
    private List<Comment> comments;
    private String status;
}
