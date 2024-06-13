package edu.icet.bo;

import edu.icet.dto.Product;
import edu.icet.dto.Supplier;

import java.util.List;

public interface ProductBo extends SuperBo{
    boolean save(Product dto);
    long getCount();
    String getLast();

    List<Product> getAll();

    Product getById(String id);

    boolean update(Product dto);

    boolean delete(Product dto);
}
