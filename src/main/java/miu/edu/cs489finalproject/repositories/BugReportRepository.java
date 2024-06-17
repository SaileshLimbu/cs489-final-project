package miu.edu.cs489finalproject.repositories;

import miu.edu.cs489finalproject.data.models.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BugReportRepository extends JpaRepository<BugReport, Long> {
    @Query(value = "SELECT * FROM bug_reports where id=:bugId and user_id=:userId", nativeQuery = true)
    Optional<BugReport> findBugReportByBugIdAndUserId(Long bugId, Long userId);
}
