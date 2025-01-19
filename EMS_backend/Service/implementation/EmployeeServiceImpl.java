package com.example.EMS_backend.Service.implementation;

import com.example.EMS_backend.Dto.EmployeeDto;
import com.example.EMS_backend.Entity.Employee;
import com.example.EMS_backend.Exception.ResourceNotFoundException;
import com.example.EMS_backend.Mapper.EmployeeMapper;
import com.example.EMS_backend.Repository.EmployeeRepository;
import com.example.EMS_backend.Service.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        //first we need to convert employeedto into employeejpa entity because we need to store employee entity into database
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto); // converted emloyeedto into employee entity
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee); // returning saved enployee to the client
    }



    @Override
    public EmployeeDto getEmployeeById(Long EmployeeId) {
        Employee employee = employeeRepository.findById(EmployeeId)
                .orElseThrow(() -> {
                    System.out.println("Employee not found, throwing exception.");
                    return new ResourceNotFoundException("Employee doesn't exist with given employee Id: " + EmployeeId);
                });
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) ->EmployeeMapper.mapToEmployeeDto(employee) )
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long EmployeeId, EmployeeDto updatedEmployee) {
       Employee employee =  employeeRepository.findById(EmployeeId).orElseThrow(
                ()->new ResourceNotFoundException("Employee is not exit with given id:"+ EmployeeId)
        );

       employee.setFirstName(updatedEmployee.getFirstName());
       employee.setLastName(updatedEmployee.getLastName());
       employee.setEmail(updatedEmployee.getEmail());

       Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long EmployeeId) {
       employeeRepository.findById(EmployeeId).orElseThrow(
                () ->
                    new ResourceNotFoundException("Employee is not exit with given id:" + EmployeeId)
                );
        employeeRepository.deleteById(EmployeeId);
    }


}
