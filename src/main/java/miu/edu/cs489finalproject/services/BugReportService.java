package miu.edu.cs489finalproject.services;

import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;

import java.util.Optional;

public interface BugReportService {
    Optional<BugReportResponseDTO> addBugReport(BugReportRequestDTO bugReportRequestDTO);
    Optional<BugReportResponseDTO> updateBugReport(BugReportRequestDTO bugReportRequestDTO, Long bugReportId);
    void deleteBugReport(Long bugReportId);
    Optional<BugReportResponseDTO> getBugReport(Long bugReportId);
}
