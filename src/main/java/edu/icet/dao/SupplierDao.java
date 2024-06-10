package edu.icet.dao;

import edu.icet.entity.SupplierEntity;

import java.util.List;

public interface SupplierDao extends CrudDao<SupplierEntity>{

    List<SupplierEntity> getAll();

}
