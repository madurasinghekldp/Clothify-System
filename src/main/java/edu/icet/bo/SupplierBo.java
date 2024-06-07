package edu.icet.bo;

import edu.icet.dto.Supplier;

public interface SupplierBo extends SuperBo{
    boolean save(Supplier dto);
}
