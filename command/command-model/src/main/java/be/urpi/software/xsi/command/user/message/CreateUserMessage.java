package be.urpi.software.xsi.command.user.message;

import be.urpi.software.xsi.command.user.metadata.CreateUser;
import be.urpi.software.xsi.core.command.impl.message.AbstractCommandMessage;

public class CreateUserMessage extends AbstractCommandMessage<CreateUser> {
    public CreateUserMessage() {
        this(null);
    }

    public CreateUserMessage(final CreateUser commandMetaData) {
        super(commandMetaData);
    }
}
