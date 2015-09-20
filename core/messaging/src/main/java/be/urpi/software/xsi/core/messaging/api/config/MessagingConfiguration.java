package be.urpi.software.xsi.core.messaging.api.config;

import be.urpi.software.xsi.core.messaging.api.service.SemaphoreService;
import be.urpi.software.xsi.core.messaging.api.service.UserService;
import be.urpi.software.xsi.core.messaging.impl.generator.BodyBasesKeyGenerator;
import be.urpi.software.xsi.core.messaging.impl.handler.ErrorHandler;
import be.urpi.software.xsi.core.messaging.impl.service.SemaphoreServiceImpl;
import be.urpi.software.xsi.core.messaging.impl.service.SystemUserService;

import com.rabbitmq.client.ConnectionFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.amqp.rabbit.config.StatefulRetryOperationsInterceptorFactoryBean;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.StatefulRetryOperationsInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.amqp.core.AcknowledgeMode.AUTO;

@Configuration
public class MessagingConfiguration {
    @Value("${amqp.host}")
    private String host;
    @Value("${amqp.port}")
    private int port;
    @Value("${amqp.username}")
    private String username;
    @Value("${amqp.password}")
    private String password;
    @Value("${amq.virtual.host}")
    private String virtualHost;

    @Bean(name = "messaging.security.system.user")
    UserService userService() {
        return new SystemUserService();
    }

    @Bean(name = "messaging.transaction.manager")
    RabbitTransactionManager messagingTransactionManager() {
        return new RabbitTransactionManager();
    }


    @Bean(name = "messaging.connection.factory")
    CachingConnectionFactory cachingConnectionFactory() {
        final CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory());
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setChannelCacheSize(25);
        cachingConnectionFactory.setPublisherConfirms(false);
        cachingConnectionFactory.setPublisherReturns(false);
        return cachingConnectionFactory;
    }

    @Bean(name = "messaging.template")
    RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setChannelTransacted(false);
        return rabbitTemplate;
    }

    @Bean(name = "messaging.handler.error")
    ErrorHandler errorHandler() {
        return new ErrorHandler();
    }

    @Bean(name = "messaging.recoverer")
    RejectAndDontRequeueRecoverer rejectAndDontRequeueRecoverer() {
        return new RejectAndDontRequeueRecoverer();
    }

    @Bean(name = "messaging.retry.interceptor.operations")
    StatefulRetryOperationsInterceptorFactoryBean statefulRetryOperationsInterceptorFactoryBean(BodyBasesKeyGenerator bodyBasesKeyGenerator, RejectAndDontRequeueRecoverer rejectAndDontRequeueRecoverer) {
        final StatefulRetryOperationsInterceptorFactoryBean statefulRetryOperationsInterceptorFactoryBean = new StatefulRetryOperationsInterceptorFactoryBean();
        statefulRetryOperationsInterceptorFactoryBean.setMessageKeyGenerator(bodyBasesKeyGenerator);
        statefulRetryOperationsInterceptorFactoryBean.setMessageRecoverer(rejectAndDontRequeueRecoverer);
        return statefulRetryOperationsInterceptorFactoryBean;
    }

    @Bean(name = "messaging.advice.chain")
    List<MethodInterceptor> adviceChain(StatefulRetryOperationsInterceptor statefulRetryOperationsInterceptor) {
        final ArrayList<MethodInterceptor> adviceChain = new ArrayList<MethodInterceptor>();
        adviceChain.add(statefulRetryOperationsInterceptor);
        return adviceChain;
    }

    @Bean(name = "messaging.policy.simple")
    SimpleRetryPolicy simpleRetryPolicy() {
        final SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(4);
        return simpleRetryPolicy;
    }

    @Bean(name = "messaging.policy.backoff")
    ExponentialBackOffPolicy exponentialBackOffPolicy() {
        final ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(1000);
        exponentialBackOffPolicy.setMaxInterval(600);
        exponentialBackOffPolicy.setMultiplier(2);
        return exponentialBackOffPolicy;
    }

    @Bean(name = "messaging.template.retry")
    RetryTemplate retryTemplate(ExponentialBackOffPolicy exponentialBackOffPolicy, SimpleRetryPolicy simpleRetryPolicy) {
        final RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        return retryTemplate;
    }

    @Bean(name = "messaging.generator.key")
    BodyBasesKeyGenerator bodyBasesKeyGenerator() {
        return new BodyBasesKeyGenerator();
    }

    @Bean(name = "messaging.listener.abstract")
    SimpleMessageListenerContainer simpleMessageListenerContainer(CachingConnectionFactory cachingConnectionFactory, RabbitTransactionManager messagingTransactionManager, ErrorHandler errorHandler, List<MethodInterceptor> adviceChain) {
        final SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setAdviceChain(adviceChain.toArray(new MethodInterceptor[adviceChain.size()]));
        simpleMessageListenerContainer.setErrorHandler(errorHandler);
        simpleMessageListenerContainer.setConnectionFactory(cachingConnectionFactory);
        simpleMessageListenerContainer.setTransactionManager(messagingTransactionManager);
        simpleMessageListenerContainer.setAcknowledgeMode(AUTO);
        simpleMessageListenerContainer.setChannelTransacted(true);
        simpleMessageListenerContainer.setConcurrentConsumers(5);
        simpleMessageListenerContainer.setRecoveryInterval(2000);
        return simpleMessageListenerContainer;
    }

    @Bean(name = "messaging.service.semaphore")
    SemaphoreService semaphoreService() {
        return new SemaphoreServiceImpl();
    }

    ConnectionFactory connectionFactory() {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setAutomaticRecoveryEnabled(true);
        return connectionFactory;
    }
}
