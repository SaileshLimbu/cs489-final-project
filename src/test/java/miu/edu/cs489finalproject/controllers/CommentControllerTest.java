package miu.edu.cs489finalproject.controllers;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import miu.edu.cs489finalproject.Cs489FinalProjectApplication;
import miu.edu.cs489finalproject.config.JwtFilter;
import miu.edu.cs489finalproject.config.JwtService;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.services.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtFilter jwtFilter;

    @Test
    void createComment() throws Exception {
        CommentRequestDTO requestDTO = new CommentRequestDTO();
        requestDTO.setText("Test comment");
        CommentResponseDTO responseDTO = new CommentResponseDTO();
        responseDTO.setText("Test comment");

        Mockito.when(jwtService.extractUserId(Mockito.any(HttpServletRequest.class))).thenReturn(1L);
        Mockito.when(commentService.addComment(Mockito.any(CommentRequestDTO.class))).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/comment")
                                .contentType("application/json")
                                .content(new Gson().toJson(requestDTO))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getAllComments() throws Exception {
        List<CommentResponseDTO> comments = new ArrayList<>();
        comments.add(new CommentResponseDTO());
        Page<CommentResponseDTO> commentPage = new PageImpl<>(comments);

        Mockito.when(commentService.getAllComments(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(commentPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comment/bug-report")
                        .param("id", "1")
                        .param("pageSize", "10")
                        .param("pageNumber", "0"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateComment() throws Exception {
        CommentRequestDTO requestDTO = new CommentRequestDTO();
        requestDTO.setText("Updated comment");
        CommentResponseDTO responseDTO = new CommentResponseDTO();
        responseDTO.setText("Updated comment");

        Mockito.when(jwtService.extractUserId(Mockito.any(HttpServletRequest.class))).thenReturn(1L);
        Mockito.when(commentService.getCommentByUser(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(responseDTO));
        Mockito.when(commentService.updateComment(Mockito.any(CommentRequestDTO.class), Mockito.anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/comment/1")
                                .contentType("application/json")
                                .content(new Gson().toJson(requestDTO))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteComment() throws Exception {
        CommentResponseDTO responseDTO = new CommentResponseDTO();

        Mockito.when(jwtService.extractUserId(Mockito.any(HttpServletRequest.class))).thenReturn(1L);
        Mockito.when(commentService.getCommentByUser(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/comment/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
