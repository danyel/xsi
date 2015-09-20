package be.urpi.software.xsi.core.command.api.gateway;

import be.urpi.software.xsi.core.command.api.metadata.CommandMetaData;
import be.urpi.software.xsi.core.command.api.user.UserInfo;

public interface CommandGateway {
    void sendCommand(CommandMetaData commandMetaData, UserInfo userInfo);
}
