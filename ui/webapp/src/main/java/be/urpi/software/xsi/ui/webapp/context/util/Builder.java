package be.urpi.software.xsi.ui.webapp.context.util;

import static com.google.common.base.Preconditions.checkNotNull;

class Builder {
    protected String context;
    protected String dispatcherName;
    protected String mapping;
    protected Integer loadOnStartup;

    static Builder applicationConfigurationBuilder() {
        return new Builder();
    }

    public Builder withContext(final String context) {
        this.context = context;
        return this;
    }

    public Builder withDispatcherName(final String dispatcherName) {
        this.dispatcherName = dispatcherName;
        return this;
    }

    public Builder withMapping(final String mapping) {
        this.mapping = mapping;
        return this;
    }

    public Builder withLoadOnStartup(final Integer loadOnStartup) {
        this.loadOnStartup = loadOnStartup;
        return this;
    }

    void afterPropertiesSet() {
        checkNotNull(context);
        checkNotNull(mapping);
        checkNotNull(dispatcherName);
        checkNotNull(loadOnStartup);
    }

    public ApplicationConfiguration build() {
        return new ApplicationConfiguration(this);
    }
}
