package be.urpi.software.xsi.core.command.api.message;

import be.urpi.software.xsi.core.command.api.metadata.CommandMetaData;
import be.urpi.software.xsi.core.messaging.api.message.TaskMessage;

public interface CommandMessage<COMMAND_METADATA extends CommandMetaData> extends TaskMessage<COMMAND_METADATA> {
}
