package org.mycompany.tinyikocarwashwebproject.admin;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin,Integer>
{
    public Long countByAdminId(Integer id);

    Admin findAdminByEmailAndPassword(String email, String password);
}
