package com.example.EMS_backend.Service;

import com.example.EMS_backend.Dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long EmployeeId);

    List<EmployeeDto> getAllEmployee();


    EmployeeDto updateEmployee(Long EmployeeId,EmployeeDto updatedEmployee);

    void deleteEmployee(Long EmployeeId);

}
