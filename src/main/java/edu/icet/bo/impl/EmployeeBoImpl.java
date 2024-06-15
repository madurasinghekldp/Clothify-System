package edu.icet.bo.impl;

import edu.icet.bo.BoFactory;
import edu.icet.bo.EmployeeBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.EmployeeDao;
import edu.icet.dto.Employee;
import edu.icet.dto.Product;
import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.util.BoType;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {

    private EmployeeDao empDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    @Override
    public boolean save(Employee dto) {
        return empDao.save(new ModelMapper().map(dto, EmployeeEntity.class));
    }

    @Override
    public long getCount() {
        return empDao.getCount();
    }

    @Override
    public String getLast() {
        return empDao.getLast();
    }

    @Override
    public Employee getById(String id) {
        EmployeeEntity employeeEntity = empDao.getById(id);
        if(employeeEntity!=null){
            return new ModelMapper().map(employeeEntity,Employee.class);
        }
        return null;
    }

    @Override
    public boolean delete(Employee dto) {
        return empDao.delete(new ModelMapper().map(dto, EmployeeEntity.class));
    }

    @Override
    public boolean update(Employee dto) {
        return empDao.update(new ModelMapper().map(dto, EmployeeEntity.class));
    }

    @Override
    public List<Employee> getAll() {
        List<EmployeeEntity> all = empDao.getAll();
        List<Employee> employees = new ArrayList<>();
        for(EmployeeEntity employeeEntity: all){
            employees.add(new ModelMapper().map(employeeEntity,Employee.class));
        }
        return employees;
    }

}
