package edu.miu.cs.cs544.exercise12_2.bank.logging;

public class Logger implements ILogger {

    public void log(String logstring) {
        java.util.logging.Logger.getLogger("BankLogger").info(logstring);
    }

}
