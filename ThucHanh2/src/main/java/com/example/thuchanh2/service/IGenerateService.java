package com.example.thuchanh2.service;

import com.example.thuchanh2.exception.DuplicateEmailException;
import com.example.thuchanh2.model.Customer;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();
    void save(T t) throws DuplicateEmailException;
    T findById(Long id);
    void deleteById(Long id);
}
