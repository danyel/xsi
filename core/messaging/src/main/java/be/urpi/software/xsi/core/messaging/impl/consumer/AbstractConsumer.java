package be.urpi.software.xsi.core.messaging.impl.consumer;

import be.urpi.software.xsi.core.messaging.api.message.TaskMessage;
import be.urpi.software.xsi.core.messaging.api.metadata.MetaData;
import be.urpi.software.xsi.core.messaging.api.service.SemaphoreService;
import be.urpi.software.xsi.core.messaging.api.service.UserService;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

public abstract class AbstractConsumer<META_DATA extends MetaData, TASK_MESSAGE extends TaskMessage<META_DATA>> implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConsumer.class);
    protected final Class<TASK_MESSAGE> messageType;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private SemaphoreService semaphoreService;
    private UserService userService;

    public AbstractConsumer(final Class<TASK_MESSAGE> messageType) {
        this.messageType = messageType;
    }

    @Override
    public void onMessage(final Message message) {
        try {
            final TASK_MESSAGE taskMessage = objectMapper.readValue(message.getBody(), messageType);
            if (semaphoreService.acquireSemaphore(taskMessage.getTaskMessageId())) {
                final META_DATA metaData = taskMessage.getMetaData();

                try {
                    userService.setUserToCurrentSession();
                    doConsume(metaData);
                } catch (Exception e) {
                    doHandleException(metaData, e);
                }

                semaphoreService.releaseSemaphore(taskMessage.getTaskMessageId());
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Transactional(propagation = REQUIRES_NEW)
    protected void doHandleException(final META_DATA metaData, final Exception e) {
        LOGGER.error(metaData.getClass().getSimpleName() + " " + e.getMessage());
        handleException(metaData, e);
    }

    @Transactional(propagation = REQUIRES_NEW)
    protected void doConsume(final META_DATA metaData) throws Exception {
        consume(metaData);
    }

    protected abstract void handleException(final META_DATA metaData, final Exception e);

    protected abstract void consume(final META_DATA metaData) throws Exception;

    public void setSemaphoreService(final SemaphoreService semaphoreService) {
        this.semaphoreService = semaphoreService;
    }

    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
