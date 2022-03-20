package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Game;

import java.util.List;

public interface GameService {
    void saveOrUpdate(Game level);
    void delete(Game level);
    List<Game> getAll();
    Game getById(Long id);
    List<Game> getAllSortedByName();
}
