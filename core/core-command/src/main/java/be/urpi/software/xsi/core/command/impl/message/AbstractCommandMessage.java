package be.urpi.software.xsi.core.command.impl.message;

import be.urpi.software.xsi.core.command.api.message.CommandMessage;
import be.urpi.software.xsi.core.command.api.metadata.CommandMetaData;

public class AbstractCommandMessage<COMMAND_META_DATA extends CommandMetaData> implements CommandMessage<COMMAND_META_DATA> {
    private final COMMAND_META_DATA commandMetaData;
    private String taskMessageId;

    public AbstractCommandMessage(final COMMAND_META_DATA commandMetaData) {
        this.commandMetaData = commandMetaData;
    }

    @Override
    public String getTaskMessageId() {
        return taskMessageId;
    }

    @Override
    public void setTaskMessageId(final String taskMessageId) {
        this.taskMessageId = taskMessageId;
    }

    @Override
    public COMMAND_META_DATA getMetaData() {
        return commandMetaData;
    }
}
