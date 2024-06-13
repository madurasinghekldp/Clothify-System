package edu.icet.bo.impl;

import edu.icet.bo.BoFactory;
import edu.icet.bo.EmployeeBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.EmployeeDao;
import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.BoType;
import edu.icet.util.DaoType;

import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {

    private EmployeeDao empDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    @Override
    public boolean save(Employee dao) {
        return false;
    }

    @Override
    public long getCount() {
        return 0;
    }

    @Override
    public String getLast() {
        return null;
    }

    @Override
    public Employee getById(String id) {
        return null;
    }

    @Override
    public boolean delete(Employee dto) {
        return false;
    }

    @Override
    public boolean update(Employee dao) {
        return false;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

}
