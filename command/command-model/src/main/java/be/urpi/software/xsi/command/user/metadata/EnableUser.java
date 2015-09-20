package be.urpi.software.xsi.command.user.metadata;

import be.urpi.software.xsi.core.command.impl.metadata.Command;

public class EnableUser extends Command {
    private String uuid;
    private boolean enable;

    public EnableUser() {
    }

    public EnableUser(final String uuid, final boolean enable) {
        this.uuid = uuid;
        this.enable = enable;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isEnable() {
        return enable;
    }

    @Override
    public String toString() {
        return "EnableUser{" +
                "uuid='" + uuid + '\'' +
                ", enable=" + enable +
                '}';
    }
}
