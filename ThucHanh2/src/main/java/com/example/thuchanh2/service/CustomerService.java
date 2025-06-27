package com.example.thuchanh2.service;

import com.example.thuchanh2.exception.DuplicateEmailException;
import com.example.thuchanh2.model.Customer;
import com.example.thuchanh2.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
      return   customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) throws DuplicateEmailException {
        try{
            customerRepository.save(customer);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateEmailException();
        }

    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
