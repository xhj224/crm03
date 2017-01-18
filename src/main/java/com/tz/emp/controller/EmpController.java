package com.tz.emp.controller;

import com.tz.emp.service.EmployeeService;
import com.tz.entity.Employee;
import com.tz.entity.EmployeePageBean;
import com.tz.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * project_name : crm04
 * user : xhj224
 * date : 2017/1/14 17:29
 */
@Controller
@RequestMapping("/emp")
public class EmpController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/{pageNow}/{pageSize}/list", method = RequestMethod.GET)
    public String listEmpGet(@PathVariable("pageNow") int pageNow, @PathVariable("pageSize") int pageSize, Model model) {
        EmployeePageBean pageBean = employeeService.pageBeanEmployees(pageNow, pageSize);
        model.addAttribute("pageBean", pageBean);
        return "/WEB-INF/jsp/view/emp_list_view.jsp";
    }

    @RequestMapping(value = "/addEmp", method = RequestMethod.GET)
    public String addEmpGet() {
        return "/WEB-INF/jsp/view/emp_add_view.jsp";
    }

    @RequestMapping(value = "/{id}/updateEmp", method = RequestMethod.GET)
    public String updateEmpGet(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmpById(id);
        model.addAttribute("employee", employee);
        return "/WEB-INF/jsp/view/emp_add_view.jsp";
    }

    @RequestMapping(value = "/{id}/deleteEmpForAjax", method = RequestMethod.GET)
    public void deleteEmpForAjaxGet(@PathVariable("id") Long id, HttpServletResponse resp) throws IOException {
        boolean result = employeeService.removeEmpById(id);
        if (result) {
            resp.getWriter().print("1");
        } else {
            resp.getWriter().print("0");
        }
    }

    @RequestMapping(value = "/{ids}/batchRemoveForAjax", method = RequestMethod.GET)
    public void batchRemoveForAjaxGet(@PathVariable("ids") String ids, HttpServletResponse resp) throws IOException {
        String[] arrIds = ids.split(":");
        List<Long> idsList = new ArrayList<>();
        for (String arrId : arrIds) {
            idsList.add(Long.parseLong(arrId));
        }
        boolean result = employeeService.beachRemoveEmps(idsList);
        if (result) {
            resp.getWriter().print("1");
        } else {
            resp.getWriter().print("0");
        }
    }

    @RequestMapping(value = "/{id}/{salary}/editSalaryForAjax", method = RequestMethod.GET)
    public void editSalaryForAjaxGet(HttpServletResponse resp, @PathVariable("id") Long id, @PathVariable("salary") double salary) throws IOException {
        boolean result = employeeService.editSalaryById(id, salary);
        if (result) {
            resp.getWriter().print("1");
        } else {
            resp.getWriter().print("0");
        }
    }

    @RequestMapping(value = "/addOrUpdateEmp", method = RequestMethod.POST)
    public String addOrUpdateEmpPost(String name, String salary, String hiredate, String title, String id, HttpServletRequest req) {
        Employee employee = new Employee(name, title, Double.parseDouble(salary), DateUtil.parseString("yyyy-MM-dd", hiredate));
        if (id != null && id.length() != 0) {
            employee.setId(Long.parseLong(id));
        }
        employeeService.addEmp(employee);
        String pageNow = req.getParameter("pageNow");
        String pageSize = req.getParameter("pageSize");
        pageNow = pageNow == null ? "1" : pageNow;
        pageSize = pageSize == null ? "2" : pageSize;
        return "redirect:/emp/" + pageNow + "/" + pageSize + "/list.do";
    }

    @RequestMapping(value = "/selectByName", method = RequestMethod.POST)
    public String selectByNamePost(String name, Model model) {
        List<Employee> employees = employeeService.findEmployessByName(name);
        model.addAttribute("employees", employees);
        return "/WEB-INF/jsp/view/emp_select_view.jsp";
    }

    @RequestMapping(value = "/selectByTitle", method = RequestMethod.POST)
    public String selectByTitlePost(String title, Model model) {
        List<Employee> employees = employeeService.findEmployessByTitle(title);
        model.addAttribute("employees", employees);
        return "/WEB-INF/jsp/view/emp_select_view.jsp";
    }

    @RequestMapping(value = "/selectBySalary", method = RequestMethod.POST)
    public String selectBySalaryPost(String sSalary, String eSalary, Model model) {
        double dsSalary, deSalary;
        if (sSalary == null || sSalary.length() == 0) {
            dsSalary = 0;
        } else {
            dsSalary = Double.parseDouble(sSalary);
        }
        if (eSalary == null || eSalary.length() == 0) {
            deSalary = Integer.MAX_VALUE;
        } else {
            deSalary = Double.parseDouble(eSalary);
        }
        List<Employee> employees = employeeService.findEmployessBySalary(dsSalary, deSalary);
        model.addAttribute("employees", employees);
        return "/WEB-INF/jsp/view/emp_select_view.jsp";
    }

    @RequestMapping(value = "/selectByHiredate", method = RequestMethod.POST)
    public String selectByHiredatePost(String sHiredate, String eHiredate, Model model) {
        Date dsHireDate, deHireDate;
        if (sHiredate == null || sHiredate.length() == 0) {
            dsHireDate = DateUtil.parseString("yyyy-MM-dd", "1970-1-1");
        } else {
            dsHireDate = DateUtil.parseString("yyyy-MM-dd", sHiredate);
        }
        if (eHiredate == null || eHiredate.length() == 0) {
            deHireDate = new Date();
        } else {
            deHireDate = DateUtil.parseString("yyyy-MM-dd", eHiredate);
        }
        List<Employee> employees = employeeService.findEmployessByHiredate(dsHireDate, deHireDate);
        model.addAttribute("employees", employees);
        return "/WEB-INF/jsp/view/emp_select_view.jsp";
    }
}
