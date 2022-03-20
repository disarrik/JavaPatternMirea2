package com.example.javapatternmirea2.controller;

import com.example.javapatternmirea2.entity.Game;
import com.example.javapatternmirea2.entity.Level;
import com.example.javapatternmirea2.service.GameService;
import com.example.javapatternmirea2.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAll();
    }

    @PostMapping("/{game_id}/addLevel")
    public void addLevelToGame(@PathVariable("game_id") Long gameId, @RequestBody Level newLevel) {
        Game game = gameService.getById(gameId);
        if (game.getLevels() != null) {
            game.getLevels().add(newLevel);
        }
        else {
            game.setLevels(new ArrayList<Level>(List.of(newLevel)));
        }
    }

    @PostMapping
    public void addGame(@RequestBody Game game) {
        gameService.saveOrUpdate(game);
    }

    @DeleteMapping
    public void removeGame(@RequestBody Game game) throws Exception {
        gameService.delete(game);
    }
}
