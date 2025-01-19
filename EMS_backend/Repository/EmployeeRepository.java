package com.example.EMS_backend.Repository;

import com.example.EMS_backend.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
