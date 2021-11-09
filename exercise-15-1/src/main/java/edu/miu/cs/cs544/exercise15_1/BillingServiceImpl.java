package edu.miu.cs.cs544.exercise15_1;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class BillingServiceImpl implements BillingService {

    @Scheduled(cron = "0/7 * * * * *")
    public void printBills() {
        Date date = Calendar.getInstance().getTime();
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        String currentTime = timeFormatter.format(date);
        System.out.println(currentTime + "    printing bills");
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void generateBillingReport() {
        Date date = Calendar.getInstance().getTime();
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        String currentTime = timeFormatter.format(date);
        System.out.println(currentTime + "    generate billing report");
    }
}
