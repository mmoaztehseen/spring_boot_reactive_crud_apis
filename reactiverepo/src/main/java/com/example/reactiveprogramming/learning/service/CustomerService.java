package com.example.reactiveprogramming.learning.service;

import com.example.reactiveprogramming.learning.entity.Customer;
import com.example.reactiveprogramming.learning.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    public Mono<ResponseEntity<Customer>> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
    public Flux<?> getAllCustomers() {
        return customerRepository.findAll()
                .flatMap(Mono::just)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No customer exist in the database")))
                .onErrorResume(ResponseStatusException.class, ex -> {
                    log.error("Message: {}", ex.getMessage());
                    return Mono.error(ex);
                }).delayElements(Duration.ofSeconds(2));
    }
    public Mono<?> saveCustomer(Customer customer){
        return customerRepository.save(customer).then(Mono.just("Created Successfully")).log();
    }
    public Mono<?> updateCustomerEmail(Long id, String email){
        return customerRepository.findById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setEmail(email);
                    return customerRepository.save(existingCustomer).then(Mono.just("Successfully Updated"));
                })
                .defaultIfEmpty("No customer exist with id: "+id);
    }
    public Mono<?> deleteCustomerById(Long id){
        return customerRepository.findById(id)
                .flatMap(customer -> customerRepository.deleteById(id).then(Mono.just("Deleted Successfully")))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with given id: " + id)))
                .onErrorResume(
                        ResponseStatusException.class, ex -> {
                        log.error("Customer not found with given id: {}", id);
                        return Mono.error(ex);
                });
    }
    public Mono<?> deleteCustomerAll(){
        return customerRepository.deleteAll().log();
    }
}
