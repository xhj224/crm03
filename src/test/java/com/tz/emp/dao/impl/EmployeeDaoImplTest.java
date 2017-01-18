package com.tz.emp.dao.impl;

import com.tz.emp.dao.EmployeeDao;
import com.tz.entity.Employee;
import com.tz.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 15:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeDaoImplTest {

    @Resource
    private EmployeeDao employeeDao;

    @Test
    public void selectAllEmps() throws Exception {
        List<Employee> employees = employeeDao.selectAllEmps();
        if (employees != null) {
            employees.forEach(System.out::println);
        }
    }

    @Test
    public void selectEmpById() throws Exception {
        Employee employee = employeeDao.selectEmpById(1L);
        System.out.println(employee);
    }

    @Test
    public void deleteEmpById() throws Exception {
        boolean bool = employeeDao.deleteEmpById(1L);
        System.out.println(bool);
    }

    @Test
    public void insertEmp() throws Exception {
        Employee employee = new Employee("jack", "总经理", 18000, DateUtil.parseString("yyyy-MM-dd", "2012-1-1"));
        boolean bool = employeeDao.insertEmp(employee);
        System.out.println(bool);
    }

    @Test
    public void selectEmpByName() throws Exception {
        List<Employee> employees = employeeDao.selectEmpByName("5");
        System.out.println(employees);
    }

    @Test
    public void selectEmpByTitle() throws Exception {
        List<Employee> employees = employeeDao.selectEmpByTitle("总经理");
        System.out.println(employees);
    }

    @Test
    public void selectEmpBySalary() throws Exception {
        List<Employee> employees = employeeDao.selectEmpBySalary(8500, 15000);
        System.out.println(employees);
    }

    @Test
    public void selectEmpByHiredate() throws Exception {
        List<Employee> employees = employeeDao.selectEmpByHiredate(
                DateUtil.parseString("yyyy-MM-dd", "2013-1-1"),
                DateUtil.parseString("yyyy-MM-dd", "2016-1-1"));
        System.out.println(employees);
    }

    @Test
    public void deleteEmps() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(9L);
        ids.add(10L);
        boolean bool = employeeDao.deleteEmps(ids);
        System.out.println(bool);
    }

    @Test
    public void pageByEmployees() throws Exception {
        List<Employee> employees = employeeDao.pageByEmployees(2, 5);
        if (employees != null) {
            employees.forEach(System.out::println);
        }
    }

    @Test
    public void getRowCount() throws Exception {
        Long count = employeeDao.getRowCount();
        System.out.println(count);
    }

    @Test
    public void updateSalaryById() throws Exception {
        Boolean bool = employeeDao.updateSalaryById(4L, 50);
        System.out.println(bool);
    }
}