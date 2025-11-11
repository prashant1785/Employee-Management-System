package com.ems.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Name is requried")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	@Column(unique = true)
	private String name;
	

	@NotBlank(message = "Email is requried")
	@Column(unique = true)
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
	private String phoneNumber;
	
	@NotBlank(message = "Department is requried")
	private String department;
	
	@Min(value = 10000, message = "Salary must be at least ₹10,000")
    @Max(value = 1000000, message = "Salary cannot exceed ₹10,00,000")
	private double salary;
	
	@PastOrPresent(message = "Date of joining cannot be in the future")
	private LocalDate dateOfJoining;

	public Employee()
	{
		super();
	
	}

	public Employee(long id, String name, String email, String phoneNumber, String department, double salary,
			LocalDate dateOfJoining) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}


	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", department=" + department + ", salary=" + salary + ", dateOfJoining=" + dateOfJoining + "]";
	}
	
}
