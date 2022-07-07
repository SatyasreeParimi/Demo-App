package com.SpringDatabase.DemoDatabase.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringDatabase.DemoDatabase.Entity.Employee;
import com.SpringDatabase.DemoDatabase.Exception.ResourceNotFoundException;
import com.SpringDatabase.DemoDatabase.Repository.UserRepository;

@RestController
@RequestMapping("/api/Employees")
public class RestApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	// //get all users
	
	@GetMapping
	public List<Employee> getAllUsers() {
		
		return this.userRepository.findAll();
		
	}
	
	//get user by ID
	
	@GetMapping("/{id}")
	public Employee getEmployeeByID(@PathVariable(value = "id") long id) {
		
		return this.userRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("User not found with given id" + id));
		
	}
	
	//create user
	
	@PostMapping
	public Employee creatEmployee (@RequestBody Employee employee) {
		return this.userRepository.save(employee);
	}
	//update user
	
	@PutMapping("/{id}")
	public Employee updateEployee(@RequestBody Employee employee, @PathVariable ("id") long id ) {
		
		Employee existingEmployee = this.userRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("user not found with id:" +id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		return this.userRepository.save(existingEmployee);
		
		
	}
	//delete user
	@DeleteMapping("/{id}")

	public ResponseEntity<Employee> deleteEmployee(@PathVariable ("id") long id) {
		
		Employee existingEmployee = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found with id:" +id));
		this.userRepository.delete(existingEmployee);
		return ResponseEntity.ok().build();
		
		
	}
}
