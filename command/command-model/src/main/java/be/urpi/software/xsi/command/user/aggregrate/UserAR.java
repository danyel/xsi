package be.urpi.software.xsi.command.user.aggregrate;

import be.urpi.software.xsi.command.user.metadata.EnableUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

import static javax.persistence.AccessType.FIELD;

@Entity
@Table(name = "td_user", schema = "command")
public class UserAR implements UserDetails {
    @Id
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "username")
    @Access(value = FIELD)
    private String username;
    @Column(name = "password")
    @Access(value = FIELD)
    private String password;
    @Column(name = "account_non_expired")
    @Access(value = FIELD)
    private boolean accountNonExpired;
    @Column(name = "account_non_locked")
    @Access(value = FIELD)
    private boolean accountNonLocked;
    @Column(name = "enabled")
    @Access(value = FIELD)
    private boolean enabled;
    @Column(name = "credentials_non_expired")
    @Access(value = FIELD)
    private boolean credentialsNonExpired;

    public UserAR() {
    }

    private UserAR(final UserARBuilder userARBuilder) {
        this.uuid = UUID.randomUUID().toString();
        this.username = userARBuilder.username;
        this.password = userARBuilder.password;
    }

    public static UserARBuilder builder() {
        return new UserARBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @SuppressWarnings("unused")
    public void handle(final EnableUser metaData) {
        enabled = metaData.isEnable();
    }

    public static class UserARBuilder {
        private String username;
        private String password;

        public UserARBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserARBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserAR build() {
            return new UserAR(this);
        }
    }
}
