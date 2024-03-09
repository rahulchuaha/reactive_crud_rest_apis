package com.springboot.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.springboot.webflux.entity.Employee;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String>{
    
}
