package be.urpi.software.xsi.command.rest.config;

import be.urpi.software.xsi.command.config.CommandConfiguration;
import be.urpi.software.xsi.command.user.config.CommandMessagingConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@Import(value = {CommandConfiguration.class, CommandMessagingConfiguration.class})
@EnableWebMvc
@ComponentScan(basePackages = {"be.urpi.software.xsi.command.rest.controller"})
public class CommandRestConfiguration {
}
