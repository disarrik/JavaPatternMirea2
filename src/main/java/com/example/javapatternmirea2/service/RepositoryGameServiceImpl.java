package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Game;
import com.example.javapatternmirea2.entity.Level;
import com.example.javapatternmirea2.repository.GameRepository;
import com.example.javapatternmirea2.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
@Primary
public class RepositoryGameServiceImpl implements GameService{
    private GameRepository gameRepository;
    private LevelRepository levelRepository;


    @Autowired
    public RepositoryGameServiceImpl(GameRepository gameRepository, LevelRepository levelRepository) {
        this.gameRepository = gameRepository;
        this.levelRepository = levelRepository;
    }

    @PostConstruct
    public void init() {
        Game game1 = new Game("1 game", new Date());
        saveOrUpdate(game1);
        List<Level> levels = List.of(
                new Level(1, "1"),
                new Level(2, "2")
        );
        levels.forEach(
                l -> {
                    l.setGame(game1);
                    levelRepository.save(l);
                }
        );
    }

    @Override
    @Transactional
    public void saveOrUpdate(Game level) {
        gameRepository.save(level);
    }

    @Override
    public void delete(Game level) {
        gameRepository.deleteById(level.getId());
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game getById(Long id) {
        return gameRepository.getById(id);
    }

    @Override
    public List<Game> getAllSortedByName() {
        return gameRepository.findAllByOrderByNameAsc();
    }
}
