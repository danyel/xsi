package be.urpi.software.xsi.command.user.consumer;

import be.urpi.software.xsi.command.user.aggregrate.UserAR;
import be.urpi.software.xsi.command.user.message.EnableUserMessage;
import be.urpi.software.xsi.command.user.metadata.EnableUser;
import be.urpi.software.xsi.command.user.repository.UserRepository;
import be.urpi.software.xsi.core.command.impl.consumer.CommandConsumer;

import javax.annotation.Resource;

public class EnableUserConsumer extends CommandConsumer<EnableUser, EnableUserMessage> {
    @Resource(name = UserRepository.NAME)
    private UserRepository userRepository;

    public EnableUserConsumer() {
        super(EnableUserMessage.class);
    }

    @Override
    protected void consume(final EnableUser metaData) throws Exception {
        final UserAR userAR = userRepository.getOne(metaData.getUuid());
        userAR.handle(metaData);
        userRepository.save(userAR);
    }
}
