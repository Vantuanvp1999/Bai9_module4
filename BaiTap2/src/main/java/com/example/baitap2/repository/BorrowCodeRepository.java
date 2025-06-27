package com.example.baitap2.repository;

import com.example.baitap2.model.BorrowCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowCodeRepository extends JpaRepository<BorrowCode, String> {

}
