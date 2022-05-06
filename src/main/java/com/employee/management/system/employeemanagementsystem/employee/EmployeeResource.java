package com.employee.management.system.employeemanagementsystem.employee;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;

@RestController
public class EmployeeResource {
    @Autowired
    private EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> retrieveUser(@PathVariable int id){
        Employee user=service.findOne(id);
        if(user==null)
        {
            throw new EmployeeNotFoundException("id-"+id);
        }

        EntityModel<Employee> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-employees"));

        //HATEOAS

        return resource;

    }
    //example if we type users/1 we will get the info of adam

    @DeleteMapping("/employees/{id}")
    public void  deleteUser(@PathVariable int id) {
        Employee user=service.deleteById(id);

        if(user==null)
            throw new EmployeeNotFoundException("id-"+id);

    }

    @PutMapping("/employees/{id}")
    public void updateUser(@RequestBody String name,@PathVariable int id){
        EmployeeService.updateEmployee(id,name);
        return;
    }


    //input-details of user
    //output - CREATED and return the created URI
    @PostMapping("/employees")
    public ResponseEntity<Object> createUser(@RequestBody Employee user){
        Employee savedUser=service.save(user);

        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

}
