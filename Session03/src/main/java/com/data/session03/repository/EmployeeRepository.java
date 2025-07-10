package com.data.session03.repository;

import com.data.session03.dto.EmployeeDTO;
import com.data.session03.dto.EmployeeInfo;
import com.data.session03.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByPhoneNumber(String phone);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);

    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT new com.data.session03.dto.EmployeeDTO(e.id, e.name, e.email, e.phoneNumber) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();

    @Query("SELECT e.name AS name, e.phoneNumber AS phone, e.salary AS salary FROM Employee e")
    List<EmployeeInfo> findAllEmployeeInfo();
}