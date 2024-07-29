package com.dmsBackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmployeeHasRoleMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_master_id")
    private DepartmentMaster department;

    @ManyToOne
    @JoinColumn(name = "branch_master_id")
    private BranchMaster branch;

    @ManyToOne
    @JoinColumn(name = "role_master_id")
    private RoleMaster role;


}
