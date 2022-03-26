package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Game;
import com.example.javapatternmirea2.entity.Level;
import com.example.javapatternmirea2.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@ManagedResource
public class SchedulerService {
    private final GameRepository gameRepository;

    @Autowired
    public SchedulerService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    @ManagedOperation
    public void saveEntitiesInFiles() throws IOException {
        File fileWithGames = new File("savedEntities/games.csv");
        fileWithGames.createNewFile();
        BufferedWriter gamesWriter = new BufferedWriter(new FileWriter(fileWithGames));
        File fileWithLevels = new File("savedEntities/levels.csv");
        fileWithLevels.createNewFile();
        BufferedWriter levelsWriter = new BufferedWriter(new FileWriter(fileWithLevels));
        List<Game> games = gameRepository.findAll();
        gamesWriter.write("name;date");
        gamesWriter.newLine();
        levelsWriter.write("name;complexity");
        levelsWriter.newLine();
        for (Game game : games){
            gamesWriter.write(game.getName() + ";" + game.getDate());
            gamesWriter.newLine();
            for (Level level : game.getLevels()) {
                levelsWriter.write(level.getLevelName() + ";" + level.getComlexity());
                levelsWriter.newLine();
            }
        }
        gamesWriter.close();
        levelsWriter.close();
    }
}
