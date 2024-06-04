package com.example.reactiveprogramming.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
public class Customer {
    @Id
    Long id;
    String name;
    Long phone;
    String email;
    String address;
    String city;
    String state;
    String country;
}
