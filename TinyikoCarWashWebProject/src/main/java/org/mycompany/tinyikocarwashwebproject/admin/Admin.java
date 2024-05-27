package org.mycompany.tinyikocarwashwebproject.admin;

import jakarta.persistence.*;

@Entity
@Table(name="admin_tbl")
public class Admin
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer adminId;
    @Column(name="first_name", length=25,nullable = false)
    private String name;
    @Column(name="last_name", length=25,nullable = false)
    private String surname;
    @Column(name="email", length=45,nullable = false,unique = true)
    private String email;
    @Column(name="password", length=15,nullable = false)
    private String password;
    @Column(name="phone_number",length = 10,unique = true)
    private String phone;
//
//    public Admin()
//    {
//
//    }
//    public Admin(String name, String surname, String email, String password, String phone) {
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.password = password;
//        this.phone = phone;
//  }


    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + getAdminId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phone=" + getPhone() +
                '}';
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
