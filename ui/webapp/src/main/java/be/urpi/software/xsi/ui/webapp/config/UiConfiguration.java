package be.urpi.software.xsi.ui.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"be.urpi.software.xsi.ui.webapp.controller"})
public class UiConfiguration {
}
