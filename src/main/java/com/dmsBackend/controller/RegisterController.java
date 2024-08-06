package com.dmsBackend.controller;
import com.dmsBackend.entity.Employee;
import com.dmsBackend.entity.EmployeeHasRoleMaster;
import com.dmsBackend.entity.RoleMaster;
import com.dmsBackend.payloads.Helper;
import com.dmsBackend.service.EmployeeHasRoleService;
import com.dmsBackend.service.EmployeeService;
import com.dmsBackend.service.RoleMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeHasRoleService employeeHasRoleService;

    @Autowired
    RoleMasterService roleMasterService;


    @PostMapping("/newRegister")
    public ResponseEntity<?> registerUser(@RequestBody Employee employee) {
        // Encode the password before saving
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setCreatedOn(Helper.getCurrentTimeStamp());
        employee.setUpdatedOn(Helper.getCurrentTimeStamp());
        try {
            // Save employee
            Employee savedEmployee = employeeService.save(employee);

            // Save roles and associate with employee
            for (RoleMaster role : employee.getRoles()) {
                RoleMaster savedRole = roleMasterService.saveRoleMaster(role);

                // Save EmployeeHasRole
                EmployeeHasRoleMaster employeeHasRole = new EmployeeHasRoleMaster();
                employeeHasRole.setEmployee(savedEmployee);
                employeeHasRole.setRole(savedRole);
                employeeHasRole.setDepartment(savedEmployee.getDepartment());
                employeeHasRole.setBranch(savedEmployee.getBranch());
                employeeHasRoleService.saved(employeeHasRole);
            }

            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving employee");
        }
    }
}


