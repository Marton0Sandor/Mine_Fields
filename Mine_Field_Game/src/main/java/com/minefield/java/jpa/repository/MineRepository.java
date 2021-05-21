package com.minefield.java.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minefield.java.jpa.domain.TopScore;

@Repository
public interface MineRepository extends CrudRepository<TopScore, Long> {
//    TopScore findByFullName(String fullName);
}
