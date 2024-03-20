package com.example.employee.demoemployee.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Handle the error and return the appropriate view or redirect
        return "error"; // Assuming you have an error.html Thymeleaf template
    }

    @PostMapping("/error")
    public String handleErrorPost() {
        // Handle the error and return the appropriate view or redirect for POST requests
        return "error"; // Assuming you have an error.html Thymeleaf template
    }

    public String getErrorPath() {
        return "/error";
    }
}
