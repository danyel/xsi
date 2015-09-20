package be.urpi.software.xsi.core.command.api.gateway;

import be.urpi.software.xsi.core.command.api.metadata.CommandMetaData;
import be.urpi.software.xsi.core.command.api.user.UserInfo;
import be.urpi.software.xsi.core.command.impl.message.AbstractCommandMessage;
import be.urpi.software.xsi.core.command.impl.producer.CommandProducer;

import org.springframework.beans.factory.annotation.Required;

public class AsynchroniousCommandGateway implements CommandGateway {
    private CommandProducer commandProducer;

    @SuppressWarnings("unchecked")
    @Override
    public void sendCommand(final CommandMetaData commandMetaData, final UserInfo userInfo) {
        commandProducer.send(new AbstractCommandMessage(commandMetaData));
    }

    @Required
    public void setCommandProducer(final CommandProducer commandProducer) {
        this.commandProducer = commandProducer;
    }
}
