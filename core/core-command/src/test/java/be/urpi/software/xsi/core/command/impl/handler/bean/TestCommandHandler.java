package be.urpi.software.xsi.core.command.impl.handler.bean;

import be.urpi.software.xsi.core.command.api.user.UserInfo;
import be.urpi.software.xsi.core.command.stereotype.CommandHandler;

import org.springframework.stereotype.Component;

@Component
public class TestCommandHandler {
    @CommandHandler
    public void testCommand(TestCommand testCommand, UserInfo userInfo) {

    }
}
