package miu.edu.cs489finalproject.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.config.JwtService;
import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.models.ResponseWrapper;
import miu.edu.cs489finalproject.services.BugReportService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bug-report")
@RequiredArgsConstructor
public class BugReportController {

    private final BugReportService bugReportService;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<BugReportResponseDTO>> createBugReport(@RequestBody BugReportRequestDTO bugReportRequestDTO, HttpServletRequest request) {
        Long userId = jwtService.extractUserId(request);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ResponseWrapper<>("error", "Unauthorized", null));
        }
        bugReportRequestDTO.setReporterId(userId);
        Optional<BugReportResponseDTO> bugReportResponseDTO = bugReportService.addBugReport(bugReportRequestDTO);
        if (bugReportResponseDTO.isPresent()) {
            return ResponseEntity.ok(new ResponseWrapper<>("success", "Bug report created successfully", bugReportResponseDTO.get()));
        } else {
            return ResponseEntity.status(404).body(new ResponseWrapper<>("error", "Failed to create bug report", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<BugReportResponseDTO>> getBugReportById(@PathVariable Long id) {
        Optional<BugReportResponseDTO> bugReport = bugReportService.getBugReport(id);
        if (bugReport.isPresent()) {
            return ResponseEntity.ok(new ResponseWrapper<>("success", "Bug report fetched successfully", bugReport.get()));
        } else {
            return ResponseEntity.status(404).body(new ResponseWrapper<>("error", "Bug report not found", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseWrapper<List<BugReportResponseDTO>>> getAllBugReports(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Page<BugReportResponseDTO> bugReports = bugReportService.getAllBugReports(pageSize, pageNumber);
        return ResponseEntity.ok(new ResponseWrapper<>("success", "All bug reports fetched successfully", bugReports.getContent()));
    }

    @DeleteMapping("/{bugId}")
    public ResponseEntity<ResponseWrapper<String>> deleteBugReportById(@PathVariable Long bugId, HttpServletRequest request) {
        Long userId = jwtService.extractUserId(request);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ResponseWrapper<>("error", "Unauthorized", null));
        }
        Optional<BugReportResponseDTO> bugReport = bugReportService.getBugReportByUser(bugId, userId);
        if (bugReport.isPresent()) {
            bugReportService.deleteBugReport(bugId);
            return ResponseEntity.ok(new ResponseWrapper<>("success", "Bug Report deleted successfully", null));
        }
        return ResponseEntity.status(404).body(new ResponseWrapper<>("error", "Bug Report not found or you are not authorized to delete this bug report", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<BugReportResponseDTO>> updateBugReportById(@RequestBody BugReportRequestDTO bugReportRequestDTO, @PathVariable Long id, HttpServletRequest request) {
        Long userId = jwtService.extractUserId(request);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ResponseWrapper<>("error", "Unauthorized", null));
        }
        Optional<BugReportResponseDTO> bugReportToUpdate = bugReportService.getBugReportByUser(id, userId);
        if (bugReportToUpdate.isPresent()) {
            modelMapper.map(bugReportRequestDTO, bugReportToUpdate.get());
            Optional<BugReportResponseDTO> response = bugReportService.updateBugReport(bugReportRequestDTO, id);
            if (response.isPresent()) {
                return ResponseEntity.ok(new ResponseWrapper<>("success", "Bug report updated successfully", response.get()));
            } else {
                return ResponseEntity.status(404).body(new ResponseWrapper<>("error", "Failed to update bug report", null));
            }
        }
        return ResponseEntity.status(404).body(new ResponseWrapper<>("error", "Bug report not found", null));
    }

}
