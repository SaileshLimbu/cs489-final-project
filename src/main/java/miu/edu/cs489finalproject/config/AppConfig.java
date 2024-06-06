package miu.edu.cs489finalproject.config;

import miu.edu.cs489finalproject.data.dtos.requests.BugReportRequestDTO;
import miu.edu.cs489finalproject.data.dtos.requests.CommentRequestDTO;
import miu.edu.cs489finalproject.data.dtos.requests.UserRequestDTO;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.UserResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.data.models.Comment;
import miu.edu.cs489finalproject.data.models.User;
import miu.edu.cs489finalproject.utils.ConversionUtil;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//        configureMappings(modelMapper);
        return modelMapper;
    }

    private void configureMappings(ModelMapper modelMapper) {
        //User to UserRequestDTO
        modelMapper.addMappings(new PropertyMap<User, UserRequestDTO>() {
            @Override
            protected void configure() {
                map().setUsername(source.getUsername());
                map().setPassword(source.getPassword());
                map().setEmail(source.getEmail());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
            }
        });
        //User to UserRequestDTO
        modelMapper.addMappings(new PropertyMap<User, UserResponseDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setUsername(source.getUsername());
                map().setEmail(source.getEmail());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
            }
        });
        //UserRequestDTO to User
        modelMapper.addMappings(new PropertyMap<UserRequestDTO, User>() {
            @Override
            protected void configure() {
                map().setUsername(source.getUsername());
                map().setPassword(source.getPassword());
                map().setEmail(source.getEmail());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
            }
        });
    }
}
