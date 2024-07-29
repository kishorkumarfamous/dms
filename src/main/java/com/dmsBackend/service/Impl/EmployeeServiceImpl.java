package com.dmsBackend.service.Impl;

import com.dmsBackend.entity.*;
import com.dmsBackend.exception.ResourceNotFoundException;
import com.dmsBackend.payloads.Helper;
import com.dmsBackend.repository.BranchMasterRepository;
import com.dmsBackend.repository.DepartmentMasterRepository;
import com.dmsBackend.repository.EmployeeRepository;
import com.dmsBackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
     private EmployeeRepository employeeRepository;

    @Autowired
    BranchMasterRepository branchMasterRepository;
    @Autowired
    DepartmentMasterRepository departmentMasterRepository;

    @Override
    public Employee save(Employee employee) {
        employee.setCreatedOn(Helper.getCurrentTimeStamp());
        employee.setUpdatedOn(Helper.getCurrentTimeStamp());
        BranchMaster branchMaster = branchMasterRepository.findById(employee.getBranch().getId()).orElseThrow(() -> new RuntimeException("Branch Not Found"));
        employee.setBranch(branchMaster);
        DepartmentMaster department = departmentMasterRepository.findById(employee.getDepartment().getId()).orElseThrow(() -> new RuntimeException("Department Not Found"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmail(String email)
    {
        return employeeRepository.findByEmail(email);
    }


    @Override
    public void deleteByIdEmployee(Integer id) {
      employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee findByIdEmp(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee not found","Id",id));
    }

}
