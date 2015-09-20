package be.urpi.software.xsi.core.messaging.impl.generator;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.MessageKeyGenerator;

public class BodyBasesKeyGenerator implements MessageKeyGenerator {
    @Override
    public Object getKey(final Message message) {
        return message.getMessageProperties().getCorrelationId();
    }
}
