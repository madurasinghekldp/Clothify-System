package edu.icet.bo.impl;

import edu.icet.bo.SupplierBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.SupplierDao;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {

    private SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
    @Override
    public boolean save(Supplier dto) {
        return supplierDao.save(new ModelMapper().map(dto, SupplierEntity.class));
    }

    @Override
    public long getCount() {
        return 0;
    }
}
