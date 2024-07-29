package com.dmsBackend.controller;

import com.dmsBackend.entity.DocumentHeader;
import com.dmsBackend.entity.Employee;
import com.dmsBackend.exception.ResourceNotFoundException;
import com.dmsBackend.payloads.ApiResponse;
import com.dmsBackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deletebyIdemployee(@PathVariable Integer id) {
        this.employeeService.deleteByIdEmployee(id);
        return new ResponseEntity(new ApiResponse("employee deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> findAllEmployee() {
        List<Employee> allEmployee = this.employeeService.findAllEmployee();
        return new ResponseEntity(allEmployee, HttpStatus.OK);
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<Employee> findByIdEmployee(@PathVariable Integer id) {
        Optional<Employee> employee1 = employeeService.findEmployeeById(id);
        return employee1.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
