package edu.miu.cs.cs544.exercise13_2.bank.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;

@Aspect
public class Logger {
    @Before("within(edu.miu.cs.cs544.exercise13_2.bank.dao.*)")
    public void logDaoMethods(JoinPoint joinPoint) {
        System.out.println("Calling "
                + joinPoint.getTarget().getClass().getName()
                + "."
                + joinPoint.getSignature().getName()
        );
    }

    @Around("within(edu.miu.cs.cs544.exercise13_2.bank.service.*)")
    public Object logExecutionDuration(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        System.out.println("Method: "
                + call.getTarget().getClass().getName()
                + "."
                + call.getSignature().getName()
                + " Execution Duration = "
                + totalTime
                + " ms"
        );
        return retVal;
    }

    @After("execution(public void edu.miu.cs.cs544.exercise13_2.bank.jms.JMSSender.sendJMSMessage(..)) && args(message)")
    public void logJMSMessage(JoinPoint joinPoint, String message) {
        System.out.println("method = "
                + joinPoint.getSignature().getName()
                + "JMS Message = "
                + message
        );
    }
}
