package miu.edu.cs489finalproject.repositories;

import miu.edu.cs489finalproject.data.models.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugReportRepository extends JpaRepository<BugReport, Long> {
}
