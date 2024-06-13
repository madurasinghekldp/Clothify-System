package edu.icet.bo;

import edu.icet.dto.Supplier;

import java.util.List;

public interface SupplierBo extends SuperBo{
    boolean save(Supplier dto);
    long getCount();
    String getLast();

    List<Supplier> getAll();

    Supplier getById(String id);

    boolean update(Supplier dto);

    boolean delete(Supplier dto);
}
