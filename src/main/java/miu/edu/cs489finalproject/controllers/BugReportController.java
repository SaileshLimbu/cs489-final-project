package miu.edu.cs489finalproject.controllers;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.services.BugReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bug-report")
@RequiredArgsConstructor
public class BugReportController {

    private final BugReportService bugReportService;

    @PostMapping
    public ResponseEntity<BugReportResponseDTO> createBugReport(@RequestBody BugReportRequestDTO bugReportRequestDTO) {
        Optional<BugReportResponseDTO> bugReportResponseDTO = bugReportService.addBugReport(bugReportRequestDTO);
        return bugReportResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BugReportResponseDTO> getBugReportById(@PathVariable Long id) {
        Optional<BugReportResponseDTO> bugReport = bugReportService.getBugReport(id);
        return bugReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
