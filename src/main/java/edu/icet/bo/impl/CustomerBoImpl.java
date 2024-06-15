package edu.icet.bo.impl;

import edu.icet.bo.BoFactory;
import edu.icet.bo.CustomerBo;
import edu.icet.dao.CustomerDao;
import edu.icet.dao.DaoFactory;
import edu.icet.dto.Customer;
import edu.icet.dto.Employee;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.BoType;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean save(Customer dto) {
        return customerDao.save(new ModelMapper().map(dto, CustomerEntity.class));
    }

    @Override
    public long getCount() {
        return customerDao.getCount();
    }

    @Override
    public String getLast() {
        return customerDao.getLast();
    }

    @Override
    public List<Customer> getAll() {
        List<CustomerEntity> all = customerDao.getAll();
        List<Customer> customers = new ArrayList<>();
        for(CustomerEntity customerEntity: all){
            customers.add(new ModelMapper().map(customerEntity,Customer.class));
        }
        return customers;
    }

    @Override
    public Customer getById(String id) {
        CustomerEntity customerEntity = customerDao.getById(id);
        if(customerEntity!=null){
            return new ModelMapper().map(customerEntity,Customer.class);
        }
        return null;
    }

    @Override
    public boolean update(Customer dto) {
        return customerDao.update(new ModelMapper().map(dto, CustomerEntity.class));
    }

    @Override
    public boolean delete(Customer dto) {
        return customerDao.delete(new ModelMapper().map(dto, CustomerEntity.class));
    }
}
