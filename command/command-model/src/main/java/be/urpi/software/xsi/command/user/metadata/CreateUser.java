package be.urpi.software.xsi.command.user.metadata;

import be.urpi.software.xsi.core.command.impl.metadata.Command;

public class CreateUser extends Command {
    private String user;
    private String password;

    public CreateUser() {
    }

    public CreateUser(final String user, final String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
