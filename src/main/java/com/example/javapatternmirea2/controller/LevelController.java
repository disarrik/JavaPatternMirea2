package com.example.javapatternmirea2.controller;

import com.example.javapatternmirea2.entity.Level;
import com.example.javapatternmirea2.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level")
public class LevelController {
    private LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        System.out.println("level constructor");
        this.levelService = levelService;
    }

    @GetMapping
    public List<Level> getAllLevels() {
        return levelService.getAll();
    }

    @DeleteMapping
    public void removeLevel(@RequestBody Level level) throws Exception {
        Level levelToDelete = levelService.getAll().stream()
                .filter(l -> l.equals(level))
                .findAny()
                .get();
        levelService.delete(levelToDelete);
    }
}
