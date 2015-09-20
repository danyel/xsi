package be.urpi.software.xsi.core.messaging.impl.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHandler implements org.springframework.util.ErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public void handleError(final Throwable throwable) {
        LOGGER.error(throwable.getMessage());
    }
}
