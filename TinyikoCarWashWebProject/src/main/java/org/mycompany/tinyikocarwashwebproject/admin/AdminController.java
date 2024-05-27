package org.mycompany.tinyikocarwashwebproject.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mycompany.tinyikocarwashwebproject.employee.Employee;
import org.mycompany.tinyikocarwashwebproject.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController
{
    @Autowired private AdminService service;

    @Autowired private AdminRepository adminRepository;

    @Autowired private EmployeeRepository employeeRepository;

    @GetMapping("/menu")
    public String goToMenu(Model model)
    {
       // service.listAll();
        return "menu";
    }

    @GetMapping("admin_menu")
    public String returnMenu(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");

        if(admin != null){
            session.setAttribute("admin", admin);
            model.addAttribute("admin", admin);
            return "admin_menu";
        }else{
            return "login_form";
        }

    }
    @GetMapping("/menu/new")
    public String goToLogin(Model model)
    {
        model.addAttribute("admin",new Admin());
        return "login_form";
    }
    @GetMapping("/login_form/new")
    public String goToCreate(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpServletRequest request, Model model)
    {
       // service.listAll();
       Admin admin = adminRepository.findAdminByEmailAndPassword(email, password);

       if(admin != null){
           HttpSession session = request.getSession(true);
           session.setAttribute("admin", admin);
           model.addAttribute("admin", admin);
           return "admin_menu";
       }else{
           return "login_form";
       }

    }
    @GetMapping("/admin_menu/new")
    public String goToAdminMenu(HttpServletRequest request, Model model)
    {
       // service.listAll();
        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle","Add new Admin");
        return "add_user";
    }

    @GetMapping("/admin_menu/back")
    public String goBack(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "admin_menu";
    }

    @GetMapping("/updateUser/new")
    public String goToUpdateMenu(Model model)
    {

        // service.listAll();
        model.addAttribute("admin",new Admin());
        return "updateUser";
    }
    @PostMapping("/add_user/save")
    public String saveAdmin(Employee employee , RedirectAttributes ra)
    {
        service.save(employee);
        //ra.addFlashAttribute("message","The user has been successfully added.");
        return "add_outcome";
    }

    @GetMapping("deleteUser/new")
    public String goToDelete(Model model, HttpServletRequest request,
                             RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");

        if(admin != null){
            model.addAttribute("admin", admin);
            return "deleteUser";
        }else{
            return "login_form";
        }

    }
    @PostMapping("/deleteUser/new")
    public String deleteUser(@RequestParam("email") String email,
                            Model model, HttpServletRequest request,
                             RedirectAttributes redirectAttributes)
    {
        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");

        Employee employee = employeeRepository.findEmployeeByEmail(email);

        if(employee != null){
            employeeRepository.deleteEmployeeByEmail(email);
            session.setAttribute("admin", admin);
            model.addAttribute("admin", admin);
            redirectAttributes.addFlashAttribute("message", "Employee Deleted Successfully!");
            return "redirect:/admin_menu";
        }else{
            redirectAttributes.addFlashAttribute("message", "Employee Does Not Exist!");
            return "redirect:/admin_menu";
        }

    }

    @PostMapping("/updateUser/new")
    public String update(@RequestParam("email") String email,
                         @RequestParam("name") String name,
                         @RequestParam("surname") String surname,
                         HttpServletRequest request, Model model)
    {
        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");

        Employee employee = employeeRepository.findEmployeeByEmail(email);

        if(employee != null){
            employee.setSurname(surname);
            employee.setName(name);
            employeeRepository.save(employee);

            session.setAttribute("admin", admin);
            model.addAttribute("admin", admin);
            return "update_outcome";

        }else{
            return "updateUser";
        }


    }



    @PostMapping("/search")
    public String seeEmployee(@RequestParam("email") String email,
                              Model model, HttpServletRequest request,
                              RedirectAttributes redirectAttributes){

        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");

        Employee employee = employeeRepository.findEmployeeByEmail(email);

        if(employee != null){
            session.setAttribute("admin", admin);
            model.addAttribute("admin", admin);
            model.addAttribute("employee", employee);
            return "employee_outcome";
        }else{
            redirectAttributes.addFlashAttribute("message", "Employee Does Not Exist!");
            return "redirect:/admin_menu";
        }

    }

    @PostMapping("/list/employees")
    public String getAllEmployees(Model model, HttpServletRequest request,
                           RedirectAttributes redirectAttributes){

        HttpSession session = request.getSession(true);
        Admin admin = (Admin)session.getAttribute("admin");

        List<Employee> employees = (List<Employee>) employeeRepository.findAll();

        if(employees != null){
            session.setAttribute("admin", admin);
            model.addAttribute("admin", admin);
            model.addAttribute("employees", employees);
            return "employees_outcome";
        }else{
            redirectAttributes.addFlashAttribute("message", "Employees Do Not Exist!");
            return "redirect:/admin_menu";
        }
    }
}
