package be.urpi.software.xsi.query.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"be.urpi.software.xsi.query.rest.controller"})
public class QueryRestConfiguration {
}
