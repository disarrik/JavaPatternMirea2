package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Level;
import com.example.javapatternmirea2.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
@Transactional
public class RepositoryLevelServiceImpl implements LevelService{
    private final LevelRepository levelRepository;
    //private EmailService emailService;

    @Autowired
    public RepositoryLevelServiceImpl(LevelRepository levelRepository/*, EmailService emailService*/) {
        this.levelRepository = levelRepository;
        /*this.emailService = emailService;*/
    }

    @Override
    public void saveOrUpdate(Level level) {
        levelRepository.save(level);
        /*emailService.sendEmail("disarra02@mail", "level added");*/
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
