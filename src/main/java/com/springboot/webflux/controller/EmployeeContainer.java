package com.springboot.webflux.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webflux.dto.EmployeeDto;
import com.springboot.webflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
                                                                                                                                                                                      
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeContainer {

    private EmployeeService employeeService;
    // built reactive save entity rest api
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);
    }

    //built reactive get employee rest api

    @GetMapping("{id}")
    public Mono<EmployeeDto> getEmployee(@PathVariable("id") String employeeId){
        return employeeService.getEmployee(employeeId);
    }

    // built reactive getAll employee rest api

    @GetMapping
    public Flux<EmployeeDto> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    // built reactive update employee 

    @PutMapping("{id}")
    public Mono<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("id") String employeeId){
        return employeeService.updateEmployee(employeeDto, employeeId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)    
    public Mono<Void> deleteEmployee(@PathVariable("id")String employeeId){
        return employeeService.deleteEmployee(employeeId);
    }
    
}
