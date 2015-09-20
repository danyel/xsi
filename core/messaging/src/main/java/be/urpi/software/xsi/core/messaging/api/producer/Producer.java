package be.urpi.software.xsi.core.messaging.api.producer;

import be.urpi.software.xsi.core.messaging.api.message.TaskMessage;

public interface Producer<MESSAGE extends TaskMessage> {
    void send(final MESSAGE object);
}
