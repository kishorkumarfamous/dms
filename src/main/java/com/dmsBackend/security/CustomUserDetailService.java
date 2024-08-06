package com.dmsBackend.security;


import com.dmsBackend.entity.Employee;
import com.dmsBackend.exception.ResourceNotFoundException;
import com.dmsBackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //loading user from database by username
        Employee employee = this.employeeRepository.findByEmail(name);
        return employee;
    }

}
