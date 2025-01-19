package com.example.EMS_backend.Mapper;

import com.example.EMS_backend.Dto.EmployeeDto;
import com.example.EMS_backend.Entity.Employee;

public class EmployeeMapper {
    // Map Employee (Entity) to EmployeeDto
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

//     Map EmployeeDto to Employee (Entity)
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
