package be.urpi.software.xsi.core.messaging.impl.service;

import be.urpi.software.xsi.core.messaging.api.service.SemaphoreService;

public class SemaphoreServiceImpl implements SemaphoreService {
    @Override
    public boolean acquireSemaphore(final String taskMessageId) {
        return false;
    }

    @Override
    public boolean releaseSemaphore(final String taskMessageId) {
        return false;
    }
}
