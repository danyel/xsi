package be.urpi.software.xsi.command.user.consumer;

import be.urpi.software.xsi.command.user.aggregrate.UserAR;
import be.urpi.software.xsi.command.user.message.CreateUserMessage;
import be.urpi.software.xsi.command.user.metadata.CreateUser;
import be.urpi.software.xsi.command.user.repository.UserRepository;
import be.urpi.software.xsi.core.command.impl.consumer.CommandConsumer;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CreateUserConsumer extends CommandConsumer<CreateUser, CreateUserMessage> {
    @Resource(name = UserRepository.NAME)
    private UserRepository userRepository;

    public CreateUserConsumer() {
        super(CreateUserMessage.class);
    }

    @Override
    protected void consume(final CreateUser metaData) throws Exception {
        final UserAR userAR = UserAR.builder()
                .withUsername(metaData.getUser())
                .withPassword(metaData.getPassword())
                .build();
        userRepository.save(userAR);
    }
}
