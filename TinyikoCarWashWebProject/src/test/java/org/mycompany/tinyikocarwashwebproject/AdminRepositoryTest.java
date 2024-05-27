package org.mycompany.tinyikocarwashwebproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mycompany.tinyikocarwashwebproject.employee.Employee;
import org.mycompany.tinyikocarwashwebproject.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AdminRepositoryTest
{
    @Autowired private EmployeeRepository repo;

    @Test
    public void testAddNew()
    {
        Employee employee = new Employee();

        employee.setName("Ruel");
        employee.setSurname("Chauke");
        employee.setPassword("ruel@23");
        employee.setEmail("ruel@gmail.com");
        employee.setPhone("0717791780");

        Employee savedEmployee = repo.save(employee);

        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getEmployeeId()).isGreaterThan(0);


    }
}
