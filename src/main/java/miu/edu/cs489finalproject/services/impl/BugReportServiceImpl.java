package miu.edu.cs489finalproject.services.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.data.models.Comment;
import miu.edu.cs489finalproject.data.models.User;
import miu.edu.cs489finalproject.repositories.BugReportRepository;
import miu.edu.cs489finalproject.repositories.UserRepository;
import miu.edu.cs489finalproject.services.BugReportService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BugReportServiceImpl implements BugReportService {

    private final BugReportRepository bugReportRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ModelMapper modelMapper;

    public List<CommentResponseDTO> commentToCommentResponseDTO(List<Comment> comments) {
        return modelMapper.map(comments, new TypeToken<List<CommentResponseDTO>>() {}.getType());
    }

    @Override
    public Optional<BugReportResponseDTO> addBugReport(BugReportRequestDTO bugReportRequestDTO) {
        Optional<User> user = userRepository.findById(bugReportRequestDTO.getReporterId());
        if (user.isPresent()) {
            BugReport bugReport = mapper.map(bugReportRequestDTO, BugReport.class);
            bugReport.setReporter(user.get());
            BugReport savedBugReport = bugReportRepository.save(bugReport);
            System.out.println("::::" + savedBugReport);

            BugReportResponseDTO bugReportResponseDTO = mapper.map(savedBugReport, BugReportResponseDTO.class);
//            bugReportResponseDTO.setCommentsDTO(commentToCommentResponseDTO(savedBugReport.getComments()));

            return Optional.of(bugReportResponseDTO);
        }
        return Optional.empty();
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
