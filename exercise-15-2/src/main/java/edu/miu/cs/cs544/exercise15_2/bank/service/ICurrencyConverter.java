package edu.miu.cs.cs544.exercise15_2.bank.service;

public interface ICurrencyConverter {
    double euroToDollars(double amount);

    double dollarsToEuros(double amount);
}
