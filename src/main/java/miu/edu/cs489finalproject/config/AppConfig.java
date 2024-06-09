package miu.edu.cs489finalproject.config;

import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.data.models.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        configureMappings(modelMapper);
        return modelMapper;
    }

    private void configureMappings(ModelMapper modelMapper) {
        //BugReport to BugReportResponseDTO
        modelMapper.addMappings(new PropertyMap<BugReport, BugReportResponseDTO>() {
            @Override
            protected void configure() {
                map().getReporterDTO().setId(source.getReporter().getId());
                map().getReporterDTO().setUsername(source.getReporter().getUsername());
                map().getReporterDTO().setEmail(source.getReporter().getEmail());
                map().getReporterDTO().setFirstName(source.getReporter().getFirstName());
                map().getReporterDTO().setLastName(source.getReporter().getLastName());
            }
        });

        //Comment to CommentResponseDTO
        modelMapper.addMappings(new PropertyMap<Comment, CommentResponseDTO>() {
            @Override
            protected void configure() {
                map().getUser().setId(source.getUser().getId());
                map().getUser().setUsername(source.getUser().getUsername());
                map().getUser().setEmail(source.getUser().getEmail());
                map().getUser().setFirstName(source.getUser().getFirstName());
                map().getUser().setLastName(source.getUser().getLastName());
            }
        });
    }
}
