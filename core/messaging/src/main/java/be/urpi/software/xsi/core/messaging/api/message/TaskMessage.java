package be.urpi.software.xsi.core.messaging.api.message;

import be.urpi.software.xsi.core.messaging.api.metadata.MetaData;

public interface TaskMessage<META_DATA extends MetaData> {
    String getTaskMessageId();

    void setTaskMessageId(String taskMessageId);

    META_DATA getMetaData();
}
