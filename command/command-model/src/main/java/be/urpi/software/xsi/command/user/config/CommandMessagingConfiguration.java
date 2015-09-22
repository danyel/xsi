package be.urpi.software.xsi.command.user.config;

import be.urpi.software.xsi.command.user.consumer.CreateUserConsumer;
import be.urpi.software.xsi.command.user.consumer.EnableUserConsumer;
import be.urpi.software.xsi.command.user.producer.CreateUserProducer;
import be.urpi.software.xsi.command.user.producer.EnableUserProducer;
import be.urpi.software.xsi.core.messaging.api.config.MessagingConfiguration;
import be.urpi.software.xsi.core.messaging.api.service.SemaphoreService;
import be.urpi.software.xsi.core.messaging.api.service.UserService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@Import(value = {MessagingConfiguration.class})
public class CommandMessagingConfiguration {
    @Bean
    CreateUserConsumer createUserConsumer(SemaphoreService semaphoreService, UserService userService) {
        final CreateUserConsumer createUserConsumer = new CreateUserConsumer();
        createUserConsumer.setUserService(userService);
        createUserConsumer.setSemaphoreService(semaphoreService);
        return createUserConsumer;
    }

    @Bean
    EnableUserConsumer createEnableUserConsumer(SemaphoreService semaphoreService, UserService userService) {
        final EnableUserConsumer enableUserConsumer = new EnableUserConsumer();
        enableUserConsumer.setUserService(userService);
        enableUserConsumer.setSemaphoreService(semaphoreService);
        return enableUserConsumer;
    }

    @Bean
    CreateUserProducer createUserProducer(RabbitTemplate messagingTemplate, RetryTemplate retryTemplate) {
        final CreateUserProducer createUserProducer = new CreateUserProducer();
        createUserProducer.setExchange("exch");
        createUserProducer.setMessagingTemplate(messagingTemplate);
        createUserProducer.setRetryTemplate(retryTemplate);
        createUserProducer.setRoutingKey("routing-key");
        return createUserProducer;
    }

    @Bean
    EnableUserProducer createEnableProducer(RabbitTemplate messagingTemplate, RetryTemplate retryTemplate) {
        final EnableUserProducer enableUserProducer = new EnableUserProducer();
        enableUserProducer.setExchange("exch");
        enableUserProducer.setMessagingTemplate(messagingTemplate);
        enableUserProducer.setRetryTemplate(retryTemplate);
        enableUserProducer.setRoutingKey("routing-key");
        return enableUserProducer;
    }

    @Bean
    SimpleMessageListenerContainer createEnableUserListener(SimpleMessageListenerContainer simpleMessageListenerContainer, CreateUserConsumer createUserConsumer) {
        simpleMessageListenerContainer.setQueueNames("queue");
        simpleMessageListenerContainer.setMessageListener(createUserConsumer);
        return simpleMessageListenerContainer;
    }
}
