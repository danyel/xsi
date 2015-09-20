package be.urpi.software.xsi.command.user.message;

import be.urpi.software.xsi.command.user.metadata.EnableUser;
import be.urpi.software.xsi.core.command.impl.message.AbstractCommandMessage;

public class EnableUserMessage extends AbstractCommandMessage<EnableUser> {
    public EnableUserMessage() {
        this(null);
    }

    public EnableUserMessage(final EnableUser commandMetaData) {
        super(commandMetaData);
    }
}
