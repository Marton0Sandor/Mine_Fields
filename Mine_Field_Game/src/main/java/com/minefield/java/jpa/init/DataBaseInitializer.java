package com.minefield.java.jpa.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minefield.java.jpa.domain.TopScore;
import com.minefield.java.jpa.repository.MineRepository;

@Service
public class DataBaseInitializer {
    private MineRepository mineRepo;

    @Autowired
    public DataBaseInitializer(MineRepository mineRepo) {
        this.mineRepo = mineRepo;
    }
    
    public void init() {
    }
}
