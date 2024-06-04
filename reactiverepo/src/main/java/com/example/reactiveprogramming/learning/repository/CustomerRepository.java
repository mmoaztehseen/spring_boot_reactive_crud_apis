package com.example.reactiveprogramming.learning.repository;

import com.example.reactiveprogramming.learning.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer,Long> {
}
