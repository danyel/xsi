package be.urpi.software.xsi.ui.webapp.context.util;

import org.slf4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import static com.google.common.base.Preconditions.checkNotNull;

public class WebApplicationInitializerConfigurationUtil {
    public static void onConfig(final Logger LOGGER, final ServletContext servletContext, final ApplicationConfiguration applicationConfiguration, final boolean addToListener, final Class... configurations) {
        checkNotNull(configurations, "Spring Java Config needed");
        checkNotNull(applicationConfiguration);
        checkNotNull(servletContext);
        final AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(configurations);
        if (addToListener) {
            servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));
        }
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
        final ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(applicationConfiguration.dispatcherName, dispatcherServlet);
        servletRegistration.addMapping(applicationConfiguration.mapping);
        servletRegistration.setLoadOnStartup(applicationConfiguration.loadOnStartup);
        logContext(LOGGER, applicationConfiguration.context, applicationConfiguration.dispatcherName);
    }

    public static ApplicationConfiguration buildApplicationConfiguration(final String context, final String dispatcherName, final String mapping, final int loadOnStartup) {
        return Builder.applicationConfigurationBuilder()
                .withContext(context)
                .withDispatcherName(dispatcherName)
                .withLoadOnStartup(loadOnStartup)
                .withMapping(mapping)
                .build();
    }

    public static void logContext(final Logger LOGGER, final String context, final String dispatcherName) {
        LOGGER.info(String.format("%s context loaded into ServletContext to servlet %s", context, dispatcherName));
    }
}
