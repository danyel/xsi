package be.urpi.software.xsi.core.command.api.config;

import be.urpi.software.xsi.core.messaging.api.config.MessagingConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {MessagingConfiguration.class})
@EnableAspectJAutoProxy
public class CoreCommandConfiguration {

}
