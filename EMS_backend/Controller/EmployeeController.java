package com.example.EMS_backend.Controller;
import com.example.EMS_backend.Dto.EmployeeDto;
import com.example.EMS_backend.Service.EmployeeService;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController // this class is capabale to handle http request
@RequestMapping("/api_employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Build Add Employee Rest API
    @PostMapping // to map the incomming http request to this method
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){ // requestbody annotatiion is used to extract the json
                                                                            // from the http request and convert the json into employee dto java obj
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    //build get employee rest api
@GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long EmployeeId){
        EmployeeDto emp = employeeService.getEmployeeById(EmployeeId);
        return ResponseEntity.ok(emp);
    }

    //build get all employee api
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeDtos);
    }

    // Build update Employee Rest api using Put mapping annotation

    @PutMapping("/{id}")
    public  ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long EmployeeId , @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto updatedEmp = employeeService.updateEmployee(EmployeeId,updatedEmployee);
        return ResponseEntity.ok(updatedEmp);
    }

    //Build delete employee rest api

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable("id") Long EmployeeId){
    employeeService.deleteEmployee(EmployeeId);
    return  ResponseEntity.ok("Employee is deleted succesfully!!!");
    }
}
