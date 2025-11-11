package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.entity.Employee;
import com.ems.repository.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService 
{
	@Autowired
	private EmployeeRepo employeeRepo;
	
	//Add Employee
	public Employee addEmployee(Employee employee)
	{
			return employeeRepo.save(employee);	
	}
	
	//Get all Employee
	public List<Employee> getAllEmployees()
	{
				return employeeRepo.findAll();
	}
	
	//Get Employee by ID
	public Employee getEmployeeById(long id) {
	    return employeeRepo.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));
	}
		//Delete Employee by ID
	public void deleteEmployeeById(long id)
	{
		
		 employeeRepo.deleteById(id);
	}
	
					//Update Employee by ID
	public Employee updateEmployeeByID(Employee updateEmployee, long id)
	{
		Employee existing = getEmployeeById(id);
		
		existing.setName(updateEmployee.getName());
		existing.setEmail(updateEmployee.getEmail());
        existing.setPhoneNumber(updateEmployee.getPhoneNumber());
        existing.setDepartment(updateEmployee.getDepartment());
        existing.setSalary(updateEmployee.getSalary());
        existing.setDateOfJoining(updateEmployee.getDateOfJoining());
        
        return employeeRepo.save(existing);
	}
	
				//Get Employee by Name
	public List<Employee> searchByName(String name)
	{
			return employeeRepo.findByNameContainingIgnoreCase(name);			
	}
						//Get Employee by department
	public List<Employee> getByDepartment(String department)
	{
			return employeeRepo.findByDepartment(department);	
	}
						// salary above a certain amount
	public List<Employee> getBySalaryAbove(double amount) 
	{
		return employeeRepo.findBySalaryGreaterThan(amount);
	}
	
					//Total count of employees
	public long getCount()
	{
		return employeeRepo.count();
	}
	
}
