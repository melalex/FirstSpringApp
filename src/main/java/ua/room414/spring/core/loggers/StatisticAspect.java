package ua.room414.spring.core.loggers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Melashchenko
 * @version 1.0 27 Apr 2017
 */
@Component
@Aspect
public class StatisticAspect {
    private Map<Class<?>, Integer> counter = new HashMap<>();

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }

    @Pointcut("execution(void ua.room414.spring.core.loggers.*.logEvent(..))")
    void allLogEventsLog() {}

    @AfterReturning("allLogEventsLog()")
    public void logBefore(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);
    }
}
