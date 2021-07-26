package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.controller.exception.CustomerNotFoundException;
import com.luv2code.springdemo.controller.pojo.CustomerRequest;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return service.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer customer;
		try {
			customer = service.getCustomer(customerId);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Bad input");
		}
		
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with the given id");
		}
		
		return customer;
	}
	
	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody CustomerRequest customerRequest) {
		Customer customer = new Customer(customerRequest.getFirstName(), customerRequest.getLastName(), customerRequest.getEmail());
		service.saveCustomer(customer);
		return customer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		service.saveCustomer(customer);
		return customer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer theCustomer;
		
		try {
			theCustomer = service.getCustomer(customerId);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Bad input");
		}
		
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer not found with the given id");
		}
		service.deleteCustomer(customerId);
		
		return "Customer deleted with id: " + customerId;
	}
	
	

}
