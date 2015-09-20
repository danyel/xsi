package be.urpi.software.xsi.core.command.impl.consumer;

import be.urpi.software.xsi.core.command.api.metadata.CommandMetaData;
import be.urpi.software.xsi.core.command.impl.message.AbstractCommandMessage;
import be.urpi.software.xsi.core.messaging.impl.consumer.AbstractConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CommandConsumer<META_DATA extends CommandMetaData, TASK_MESSAGE extends AbstractCommandMessage<META_DATA>> extends AbstractConsumer<META_DATA, TASK_MESSAGE> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandConsumer.class);

    public CommandConsumer(Class<TASK_MESSAGE> type) {
        super(type);
    }

    @Override
    protected void handleException(final META_DATA metaData, final Exception e) {
        LOGGER.error(messageType.getSimpleName() + " " + metaData.toString() + " " + e.getMessage());
    }
}
