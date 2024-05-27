package org.mycompany.tinyikocarwashwebproject.employee;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    Employee findEmployeeByEmail(String email);
    @Transactional
    void deleteEmployeeByEmail(String email);
}
