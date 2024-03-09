package com.springboot.webflux.service.impl;

import org.springframework.stereotype.Service;

import com.springboot.webflux.dto.EmployeeDto;
import com.springboot.webflux.entity.Employee;
import com.springboot.webflux.mapper.Employeemapper;
import com.springboot.webflux.repository.EmployeeRepository;
import com.springboot.webflux.service.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

     private EmployeeRepository employeeRepository;

    @Override
    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
        
        // convert employeeDto to emplyee entity
        Employee employee = Employeemapper.mapToEmployee(employeeDto);
        Mono<Employee> savedEmployee = employeeRepository.save(employee);
        return savedEmployee.map((emplyeeEntity) -> Employeemapper.mapToEmployeeDto(emplyeeEntity));
    }

    @Override
    public Mono<EmployeeDto> getEmployee(String employeeId) {
       Mono<Employee> savedEmployee = employeeRepository.findById(employeeId);
       return savedEmployee.map((employee) -> Employeemapper.mapToEmployeeDto((employee)));
    }

    @Override
    public Flux<EmployeeDto> getAllEmployee() {
       Flux<Employee> employeeFux =  employeeRepository.findAll();
       return employeeFux.map((employee) -> Employeemapper.mapToEmployeeDto(employee))
       .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId) {
        
        Mono<Employee> employeeMono = employeeRepository.findById(employeeId);
        Mono<Employee> updatedEmployee = employeeMono.flatMap((existingEmployee) -> {
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setEmail(employeeDto.getEmail());


        });

        return updatedEmployee.map((employee) -> Employeemapper.mapToEmployeeDto(employee));
    }

    @Override
    public Mono<Void> deleteEmployee(String employeeId) {
        return employeeRepository.deleteById(employeeId);
    }
    
}
