package edu.icet.bo.impl;

import edu.icet.bo.ProductBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.ProductDao;
import edu.icet.dao.SupplierDao;
import edu.icet.dto.Product;
import edu.icet.dto.Supplier;
import edu.icet.entity.ProductEntity;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBo {

    private ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    @Override
    public boolean save(Product dto) {
        return productDao.save(new ModelMapper().map(dto, ProductEntity.class));
    }

    @Override
    public long getCount() {
        return productDao.getCount();
    }

    @Override
    public String getLast() {
        return productDao.getLast();
    }

    @Override
    public List<Product> getAll() {
        List<ProductEntity> all = productDao.getAll();
        List<Product> products = new ArrayList<>();
        for(ProductEntity productEntity: all){
            products.add(new ModelMapper().map(productEntity,Product.class));
        }
        return products;
    }

    @Override
    public Product getById(String id) {
        ProductEntity productEntity = productDao.getById(id);
        if(productEntity!=null){
            return  new ModelMapper().map(productEntity,Product.class);
        }
        return null;
    }

    @Override
    public boolean update(Product dto) {
        return productDao.update(new ModelMapper().map(dto,ProductEntity.class));
    }
}
