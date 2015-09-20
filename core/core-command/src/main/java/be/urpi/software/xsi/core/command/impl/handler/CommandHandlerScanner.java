package be.urpi.software.xsi.core.command.impl.handler;

import be.urpi.software.xsi.core.command.api.metadata.CommandMetaData;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CommandHandlerScanner {
    @Around("@annotation(be.urpi.software.xsi.core.command.stereotype.CommandHandler)")
    public Object handleCommandHandlerScanner(final ProceedingJoinPoint pjp) throws Throwable {
        final Object proceed = pjp.proceed();
        final Object[] pjpArgs = pjp.getArgs();

        for (Object pjpArg : pjpArgs) {
            if (pjpArg instanceof CommandMetaData) {
                System.out.println(pjp.toString());
            }
        }
        return proceed;
    }
}
