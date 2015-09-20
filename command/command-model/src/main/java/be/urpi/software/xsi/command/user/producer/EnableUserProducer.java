package be.urpi.software.xsi.command.user.producer;

import be.urpi.software.xsi.command.user.message.EnableUserMessage;
import be.urpi.software.xsi.core.command.impl.producer.CommandProducer;

import org.springframework.stereotype.Component;

@Component
public class EnableUserProducer extends CommandProducer<EnableUserMessage> {
}
