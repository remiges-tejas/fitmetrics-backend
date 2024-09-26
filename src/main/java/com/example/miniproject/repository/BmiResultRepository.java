package com.example.miniproject.repository;

import com.example.miniproject.model.BmiResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BmiResultRepository extends JpaRepository<BmiResult, Long> {

}