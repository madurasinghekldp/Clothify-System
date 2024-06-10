package edu.icet.bo.impl;

import edu.icet.bo.SupplierBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.SupplierDao;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBo {

    private SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
    @Override
    public boolean save(Supplier dto) {
        return supplierDao.save(new ModelMapper().map(dto, SupplierEntity.class));
    }

    @Override
    public long getCount() {
        return supplierDao.getCount();
    }

    @Override
    public String getLast() {
        return supplierDao.getLast();
    }

    @Override
    public List<Supplier> getAll() {
        List<SupplierEntity> all = supplierDao.getAll();
        List<Supplier> suppliers = new ArrayList<>();
        for(SupplierEntity supplierEntity: all){
            suppliers.add(new ModelMapper().map(supplierEntity,Supplier.class));
        }
        return suppliers;
    }

    @Override
    public Supplier getById(String id) {
        SupplierEntity supplierEntity = supplierDao.getById(id);
        if(supplierEntity!=null){
            return  new ModelMapper().map(supplierEntity,Supplier.class);
        }
        return null;
    }

    @Override
    public boolean update(Supplier dto) {
        return supplierDao.update(new ModelMapper().map(dto,SupplierEntity.class));
    }

}
