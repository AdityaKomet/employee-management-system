package com.employee.management.system.employeemanagementsystem.employee;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeService {
    private static List<Employee> employees=new ArrayList<>();

    private static int employCount=3;
    static {
        employees.add(new Employee(1,"Aditya",new Date()));
        employees.add(new Employee(2,"Raj",new Date()));
        employees.add(new Employee(3,"Saurabh",new Date()));

    }

    public List<Employee> findAll(){
        return employees;
    }

    public Employee save(Employee user){
        if(user.getId()==null){
            user.setId(++employCount);
        }
        employees.add(user);
        return user;
    }

    public Employee findOne(int id){
        for(Employee user:employees)
        {
            if(user.getId()==id){
                return user;
            }

        }
        return null;
    }



    public Employee deleteById(int id){
        Iterator<Employee> iterator=employees.iterator();
        while(iterator.hasNext())
        {   Employee user=iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }

        }
        return null;
    }

    public static void updateEmployee(int id, String name)
    {
        for(int i=0;i<employees.size();i++)
        {
            Employee e=employees.get(i);
            if(e.getId().equals(id))
            {
                employees.set(i,e.setName(name));
            }
            return;
        }

    }

}
