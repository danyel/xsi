package be.urpi.software.xsi.ui.webapp.context.util;

public class ApplicationConfiguration {
    protected final String context;
    protected final String dispatcherName;
    protected final String mapping;
    protected final int loadOnStartup;

    ApplicationConfiguration(final Builder builder) {
        builder.afterPropertiesSet();
        context = builder.context;
        dispatcherName = builder.dispatcherName;
        mapping = builder.mapping;
        loadOnStartup = builder.loadOnStartup;
    }
}
