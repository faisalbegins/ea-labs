package edu.miu.cs.cs544.exercise11_2;

public interface IProductService {
    Product getProduct(int productNumber);
    public int getNumberInStock(int productNumber);
}
