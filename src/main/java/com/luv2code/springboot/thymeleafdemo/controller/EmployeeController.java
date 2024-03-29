package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {


        theModel.addAttribute("employees", employeeService.findAll());

        return "employees/list-employees";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Employee employee = new Employee();

        theModel.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model) {

        Employee employee = employeeService.findById(employeeId);

        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees/list";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {

        employeeService.delete(employeeId);
        return "redirect:/employees/list";
    }
}
