package miu.edu.cs489finalproject.controllers;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import miu.edu.cs489finalproject.config.JwtService;
import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.services.BugReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BugReportController.class)
@ComponentScan(basePackages = "miu.edu.cs489finalproject")
class BugReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BugReportService bugReportService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void createBugReport() throws Exception {
        BugReportRequestDTO requestDTO = new BugReportRequestDTO();
        requestDTO.setTitle("Test Bug Report");
        BugReportResponseDTO responseDTO = new BugReportResponseDTO();
        responseDTO.setTitle("Test Bug Report");

        Mockito.when(jwtService.extractUserId(Mockito.any(HttpServletRequest.class))).thenReturn(1L);
        Mockito.when(bugReportService.addBugReport(Mockito.any(BugReportRequestDTO.class))).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/bug-report")
                                .contentType("application/json")
                                .content(new Gson().toJson(requestDTO))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBugReportById() throws Exception {
        BugReportResponseDTO responseDTO = new BugReportResponseDTO();
        responseDTO.setTitle("Test Bug Report");

        Mockito.when(bugReportService.getBugReport(Mockito.anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bug-report/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllBugReports() throws Exception {
        List<BugReportResponseDTO> bugReports = new ArrayList<>();
        bugReports.add(new BugReportResponseDTO());
        Page<BugReportResponseDTO> bugReportPage = new PageImpl<>(bugReports);

        Mockito.when(bugReportService.getAllBugReports(Mockito.anyInt(), Mockito.anyInt())).thenReturn(bugReportPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bug-report/all")
                        .param("pageSize", "10")
                        .param("pageNumber", "0"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateBugReportById() throws Exception {
        BugReportRequestDTO requestDTO = new BugReportRequestDTO();
        requestDTO.setTitle("Updated Bug Report");
        BugReportResponseDTO responseDTO = new BugReportResponseDTO();
        responseDTO.setTitle("Updated Bug Report");

        Mockito.when(jwtService.extractUserId(Mockito.any(HttpServletRequest.class))).thenReturn(1L);
        Mockito.when(bugReportService.getBugReportByUser(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(responseDTO));
        Mockito.when(bugReportService.updateBugReport(Mockito.any(BugReportRequestDTO.class), Mockito.anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/bug-report/1")
                                .contentType("application/json")
                                .content(new Gson().toJson(requestDTO))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteBugReportById() throws Exception {
        BugReportResponseDTO responseDTO = new BugReportResponseDTO();

        Mockito.when(jwtService.extractUserId(Mockito.any(HttpServletRequest.class))).thenReturn(1L);
        Mockito.when(bugReportService.getBugReportByUser(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/bug-report/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
