package com.example.reactiveprogramming.learning.controller;

import com.example.reactiveprogramming.learning.entity.Customer;
import com.example.reactiveprogramming.learning.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rest/api")
public class CustomerController {
    @Autowired
    public CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<?> saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }
    @GetMapping(value = "/get",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<?> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> updateCustomerEmail(@PathVariable Long id, @RequestParam String email){
        return customerService.updateCustomerEmail(id,email);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomerById(id);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> deleteCustomer(){
        return customerService.deleteCustomerAll();
    }
}
