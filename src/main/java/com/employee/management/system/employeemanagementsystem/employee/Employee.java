package com.employee.management.system.employeemanagementsystem.employee;

import javax.persistence.GeneratedValue;
import java.util.Date;

public class Employee {
    @GeneratedValue
    private Integer id;


    private String name;

    //@ApiModelProperty(notes="Birth date should be in the past")
    private Date birthDate;


    protected Employee() {

    }

    public Employee(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return null;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
    }
}
