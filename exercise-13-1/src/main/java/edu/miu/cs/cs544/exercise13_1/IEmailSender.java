package edu.miu.cs.cs544.exercise13_1;

public interface IEmailSender {
    void sendEmail(String email, String message);

    String getOutgoingMailServer();
}