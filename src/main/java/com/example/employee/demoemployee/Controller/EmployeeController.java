package com.example.employee.demoemployee.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.employee.demoemployee.Entity.Employee;
import com.example.employee.demoemployee.Repository.EmployeeRepository;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddForm(Employee employee) {
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@Validated Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }

        employeeRepository.save(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));

        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @Validated Employee employee,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update-employee";
        }

        employeeRepository.save(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));

        employeeRepository.delete(employee);
        return "redirect:/employees/";
    }
}