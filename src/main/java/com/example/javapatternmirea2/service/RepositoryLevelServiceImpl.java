package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Level;
import com.example.javapatternmirea2.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class RepositoryLevelServiceImpl implements LevelService{
    private LevelRepository levelRepository;

    @Autowired
    public RepositoryLevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public void saveOrUpdate(Level level) {
        levelRepository.save(level);
    }

    @Override
    public void delete(Level level) {
        levelRepository.deleteById(level.getId());
    }

    @Override
    public List<Level> getAll() {
        return levelRepository.findAll();
    }
}
