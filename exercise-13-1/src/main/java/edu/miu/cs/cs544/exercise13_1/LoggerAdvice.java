package edu.miu.cs.cs544.exercise13_1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.util.Date;

@Aspect
public class LoggerAdvice {
    @After("execution(public void edu.miu.cs.cs544.exercise13_1.EmailSender.sendEmail(..)) && args(email, message)")
    public void afterEmailSent(JoinPoint joinPoint, String email, String message) {
        EmailSender emailSender = (EmailSender) joinPoint.getTarget();
        System.out.println(
                new Date()
                + " method = " + joinPoint.getSignature().getName()
                + " address = " + email
                + " message = " + message
                + " outgoing mail server = " + emailSender.getOutgoingMailServer()
        );
    }
}
