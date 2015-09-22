package be.urpi.software.xsi.command.config;

import be.urpi.software.xsi.command.user.repository.UserRepository;
import be.urpi.software.xsi.command.user.repository.UserRepositoryImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "be.urpi.software.xsi.command")
public class CommandConfiguration {
    @Bean(name = UserRepository.NAME)
    UserRepositoryImpl userRepository() {
        return new UserRepositoryImpl();
    }
}
