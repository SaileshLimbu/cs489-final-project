package miu.edu.cs489finalproject.config;

import lombok.RequiredArgsConstructor;
import miu.edu.cs489finalproject.data.dtos.responses.BugReportResponseDTO;
import miu.edu.cs489finalproject.data.dtos.responses.CommentResponseDTO;
import miu.edu.cs489finalproject.data.models.BugReport;
import miu.edu.cs489finalproject.data.models.Comment;
import miu.edu.cs489finalproject.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailService());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailService() {
        return username -> userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

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
