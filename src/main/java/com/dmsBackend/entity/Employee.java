package com.dmsBackend.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "employee_Id")
    private String employeeId;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "mobile")
    private String mobile;
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "isActive")
    private int isActive;

    @Column(name = "otp")
    private int otp;
    @Column(name="createdOn")
    private Timestamp createdOn;
    @Column(name = "updatedOn")
    private Timestamp updatedOn;

    @ManyToOne
    @JoinColumn(name = "department_master_id")
    private DepartmentMaster department;

    @ManyToOne
    @JoinColumn(name = "department_master_branch_Master_Id")
    private BranchMaster branch;
}
