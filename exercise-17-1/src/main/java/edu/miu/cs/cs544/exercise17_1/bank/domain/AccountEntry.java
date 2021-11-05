package edu.miu.cs.cs544.exercise17_1.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntry {
    private Date date;
    private double amount;
    private String description;
    private String fromAccountNumber;
    private String fromPersonName;
}
