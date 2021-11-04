package edu.miu.cs.cs544.exercise11_3;

public interface IBookSupplier {
    double computePrice(String isbn);

    void order(Book book);
}
