package edu.miu.cs.cs544.exercise11_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    public final List<IBookSupplier> suppliers;

    @Autowired
    public BookService(List<IBookSupplier> suppliers) {
        this.suppliers = suppliers;
    }

    public void buy(Book book) {
        double lowestPrice = 0;
        IBookSupplier cheapestSupplier = null;
        // find the cheapest supplier
        for (IBookSupplier supplier : suppliers) {
            double price = supplier.computePrice(book.getIsbn());
            if (cheapestSupplier == null) {
                cheapestSupplier = supplier;
                lowestPrice = price;
            } else {
                if (price < lowestPrice) {
                    cheapestSupplier = supplier;
                    lowestPrice = price;
                }
            }
        }
        // buy with the cheapest supplier
        if (cheapestSupplier != null) {
            cheapestSupplier.order(book);
        }
    }
}