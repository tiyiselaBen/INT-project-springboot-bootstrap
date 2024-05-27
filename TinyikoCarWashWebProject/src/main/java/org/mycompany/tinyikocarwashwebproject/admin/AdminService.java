package org.mycompany.tinyikocarwashwebproject.admin;

import org.mycompany.tinyikocarwashwebproject.employee.Employee;
import org.mycompany.tinyikocarwashwebproject.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService
{
    @Autowired private AdminRepository repo;
    @Autowired private EmployeeRepository repos;


    public List<Employee> listAll()
    {
        return (List<Employee>) repos.findAll();
    }
    public void save(Employee employee)
    {
        repos.save(employee);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Optional<Employee> result=repos.findById(id);
        if(result.isPresent())
        {
            repos.deleteById(id);
        }
        else{
            throw new UserNotFoundException("Could not find any user with ID." + id);
        }

    }
//    public boolean updateUser(String email){
////        Optional<Employee> result=repos.findById(id);
////        if(result.isPresent())
////        {
////            repos.deleteById(id);
////        }
//        boolean update = false;
//        Iterable<Employee> employees = repos.findAll();
//        for (Employee e: employees)
//        {
//            if(e.equals(email))
//            {
//                update = true;
//            }
//        }
//        return update;
//    }
    public Employee get(Integer id)
    {
        Optional<Employee> result = repos.findById(id);
        if(result.isPresent())
        {
            return result.get();
        }
        return new Employee();
    }
}
