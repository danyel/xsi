package be.urpi.software.xsi.core.messaging.impl.producer;

import be.urpi.software.xsi.core.messaging.api.message.TaskMessage;
import be.urpi.software.xsi.core.messaging.api.producer.Producer;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.retry.support.RetryTemplate;

import java.util.Random;

import static org.springframework.amqp.core.MessageDeliveryMode.PERSISTENT;

public class AbstractProducer<MESSAGE extends TaskMessage> implements Producer<MESSAGE> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractProducer.class);
    private final Random randomSeed = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String exchange;
    private String routingKey;
    private RabbitTemplate messagingTemplate;
    private RetryTemplate retryTemplate;

    @Override
    public void send(final MESSAGE object) {
        final long correlationId = randomSeed.nextLong();

        try {
            retryTemplate.execute(retryContext -> {
                final MessageProperties messageProperties = new MessageProperties();
                final String id = String.valueOf(correlationId);
                object.setTaskMessageId(id);
                messageProperties.setCorrelationId(id.getBytes());
                messageProperties.setDeliveryTag(correlationId);
                messageProperties.setDeliveryMode(PERSISTENT);
                messageProperties.setMessageCount(1);
                messagingTemplate.send(exchange, routingKey, new Message(objectMapper.writeValueAsBytes(object), messageProperties), new CorrelationData(id));
                return null;
            });
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public void setRetryTemplate(final RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    public void setMessagingTemplate(final RabbitTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void setExchange(final String exchange) {
        this.exchange = exchange;
    }

    public void setRoutingKey(final String routingKey) {
        this.routingKey = routingKey;
    }
}
