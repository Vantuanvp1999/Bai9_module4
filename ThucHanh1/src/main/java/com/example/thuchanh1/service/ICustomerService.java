package com.example.thuchanh1.service;

import com.example.thuchanh1.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll() throws Exception;
    Customer findOne(long    id) throws Exception;
}
