package miu.edu.cs489finalproject.services.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.repositories.BugReportRepository;
import miu.edu.cs489finalproject.services.BugReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BugReportServiceImpl implements BugReportService {

    private final BugReportRepository bugReportRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<BugReportResponseDTO> addBugReport(BugReportRequestDTO bugReportRequestDTO) {
        BugReport bugReport = mapper.map(bugReportRequestDTO, BugReport.class);
        BugReport savedBugReport = bugReportRepository.save(bugReport);
        return Optional.of(mapper.map(savedBugReport, BugReportResponseDTO.class));
    }

    @Override
    public Optional<BugReportResponseDTO> updateBugReport(BugReportRequestDTO bugReportRequestDTO, Long bugReportId) {
        Optional<BugReport> bugReport = bugReportRepository.findById(bugReportId);
        if (bugReport.isPresent()) {
            BugReport updatedBugReport = mapper.map(bugReportRequestDTO, BugReport.class);
            updatedBugReport.setId(bugReportId);
            return Optional.of(mapper.map(bugReportRepository.save(updatedBugReport), BugReportResponseDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void deleteBugReport(Long bugReportId) {
        bugReportRepository.deleteById(bugReportId);
    }

    @Override
    public Optional<BugReportResponseDTO> getBugReport(Long bugReportId) {
        return Optional.of(mapper.map(bugReportRepository.findById(bugReportId), BugReportResponseDTO.class));
    }
}
