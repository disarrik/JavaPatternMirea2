package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Level;

import java.util.List;

public interface LevelService {
    void saveOrUpdate(Level level);
    void delete(Level level);
    List<Level> getAll();

}
