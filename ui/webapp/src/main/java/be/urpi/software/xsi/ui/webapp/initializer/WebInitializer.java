package be.urpi.software.xsi.ui.webapp.initializer;

import be.urpi.software.xsi.command.rest.config.CommandRestConfiguration;
import be.urpi.software.xsi.query.rest.config.QueryRestConfiguration;
import be.urpi.software.xsi.ui.webapp.config.UiConfiguration;
import be.urpi.software.xsi.ui.webapp.context.util.ApplicationConfiguration;
import be.urpi.software.xsi.ui.webapp.context.util.WebApplicationInitializerConfigurationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/*
public class CustomWebAppInitializer implements WebApplicationInitializer {
@Override
public void onStartup(ServletContext container) {
XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
rootContext.setConfigLocations(new String[]{"classpath*:META-INF/spring/applicationContext-security.xml", "classpath*:META-INF/spring/applicationContext.xml"});
container.addListener(new ContextLoaderListener(rootContext));
ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", DispatcherServlet.class);
dispatcher.setInitParameter("contextConfigLocation", "/WEB-INF/spring/webmvc-config.xml");
dispatcher.addMapping("/");
container.addFilter("Spring OpenEntityManagerInViewFilter", org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter.class)
.addMappingForUrlPatterns(null, false, "/*");
container.addFilter("HttpMethodFilter", org.springframework.web.filter.HiddenHttpMethodFilter.class)
.addMappingForUrlPatterns(null, false, "/*");
container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
.addMappingForUrlPatterns(null, false, "/*");
FilterRegistration charEncodingfilterReg = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
charEncodingfilterReg.setInitParameter("forceEncoding", "true");
charEncodingfilterReg.addMappingForUrlPatterns(null, false, "/*");
}
}
 */
public class WebInitializer implements WebApplicationInitializer {
    private static Logger logger = LoggerFactory.getLogger(WebInitializer.class);

    public void onStartup(final ServletContext servletContext) throws ServletException {
        initUiContext(servletContext);
        initQueryRestContext(servletContext);
        initCommandRestContext(servletContext);
        System.setProperty("spring.profiles.active", "dev");
    }

    private void initCommandRestContext(final ServletContext servletContext) {
        final ApplicationConfiguration commandApplicationConfiguration = WebApplicationInitializerConfigurationUtil.buildApplicationConfiguration("Command Rest Controller", "commandRestDispatcher", "/command/*", 3);
        WebApplicationInitializerConfigurationUtil.onConfig(logger, servletContext, commandApplicationConfiguration, false, CommandRestConfiguration.class);
    }

    private void initQueryRestContext(final ServletContext servletContext) {
        final ApplicationConfiguration commandApplicationConfiguration = WebApplicationInitializerConfigurationUtil.buildApplicationConfiguration("Query Rest Controller", "queryRestDispatcher", "/query/*", 2);
        WebApplicationInitializerConfigurationUtil.onConfig(logger, servletContext, commandApplicationConfiguration, false, QueryRestConfiguration.class);
    }

    private void initUiContext(final ServletContext servletContext) {
        final ApplicationConfiguration commandApplicationConfiguration = WebApplicationInitializerConfigurationUtil.buildApplicationConfiguration("UI Web", "uiDispatcher", "/ui/*", 1);
        WebApplicationInitializerConfigurationUtil.onConfig(logger, servletContext, commandApplicationConfiguration, false, UiConfiguration.class);
    }
}
