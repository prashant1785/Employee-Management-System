package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Employee;
import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee, Long>
{
	 public  List<Employee> findByNameContainingIgnoreCase(String name);
	 public  List<Employee> findByDepartment(String department);
	 public List<Employee> findBySalaryGreaterThan(double amount);
}
