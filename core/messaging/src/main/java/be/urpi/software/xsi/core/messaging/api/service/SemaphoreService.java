package be.urpi.software.xsi.core.messaging.api.service;

public interface SemaphoreService {
    boolean acquireSemaphore(String taskMessageId);

    boolean releaseSemaphore(String taskMessageId);
}
