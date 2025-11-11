package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Employee;
import com.ems.service.EmployeeService;

import jakarta.validation.Valid;

	@RestController
	public class EmployeeController 
	{
		@Autowired
		private EmployeeService employeeService;
		
		@PostMapping("/addEmployee")			//Add Employees
		public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee)
		{
			try {
				Employee result =  employeeService.addEmployee(employee);
				return new ResponseEntity<Employee>(result,HttpStatus.CREATED);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST); 
			}
		}
		
		@GetMapping("/getAllEmployees") 		//Get all Employees
		public ResponseEntity<List<Employee>> getAllEmployees()
		{
			try {
				return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
			}
			catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
			}
		}
		
		@GetMapping("/getEmployeeById/{id}")			//Get Employee by ID
		public ResponseEntity<Employee> getEmployeeById(@Valid @PathVariable long id) {
		    Employee employee = employeeService.getEmployeeById(id);
		    return ResponseEntity.ok(employee);
		}
		
		@DeleteMapping("/deleteEmployeeById/{id}")			// Delete Employee by ID
		public ResponseEntity<?> deleteEmployeeById(@Valid @PathVariable long id)
		{
			try {
				employeeService.deleteEmployeeById(id);
				return ResponseEntity.ok().build();
			} 
			catch (Exception e) {
			return ResponseEntity.notFound().build();
			}
		}
		
		@PutMapping("/updateEmployeeById/{id}")		//Update Employee by ID
		public ResponseEntity<Employee> updateEmployeeById(@Valid @RequestBody Employee employee, @Valid @PathVariable long id)
		{
			try {
				return ResponseEntity.ok(employeeService.updateEmployeeByID(employee, id));
			} 
			catch (Exception e) 
			{
				return ResponseEntity.notFound().build();
				
			}
		}
		
		@GetMapping("/searchByName")					//Get Employee by Name
		public ResponseEntity<List<Employee>> searchByName(@Valid @RequestParam String name)
		{
			 List<Employee> employees = employeeService.searchByName(name);
			    if (employees.isEmpty()) {
			        return ResponseEntity.notFound().build();
			    }
			    return ResponseEntity.ok(employees);
		}
		
		@GetMapping("/getByDepartment/{department}")		//Get Employee by department
		public ResponseEntity<List<Employee>> getByDepartment(@Valid @PathVariable String department )
		{
			 List<Employee> list = employeeService.getByDepartment(department);
			    if (list.isEmpty()) {
			        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			    }
			    return ResponseEntity.ok(list);
		}
		
		@GetMapping("/getBySalaryAbove/{amount}")								// salary above a certain amount
		 public ResponseEntity<List<Employee>> getBySalaryAbove(@Valid @PathVariable double amount) 
		 {
			 List<Employee> list = employeeService.getBySalaryAbove(amount);
			 if(list.isEmpty())
			 {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			 }
			 return ResponseEntity.ok(list);
		 }
		
		@GetMapping("/getCount")			// Total count of employees
		public ResponseEntity<?> getCount()
		{
			try {
				return ResponseEntity.ok(employeeService.getCount());
			} 
			catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		}
}
