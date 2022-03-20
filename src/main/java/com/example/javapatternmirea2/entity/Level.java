package com.example.javapatternmirea2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue
    private Long id;
    private int comlexity;
    private String levelName;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "game_id")
    private Game game;

    public int getComlexity() {
        return comlexity;
    }

    public Level(int comlexity, String levelName) {
        this.comlexity = comlexity;
        this.levelName = levelName;
    }

    public Level() {
    }

    public void setComlexity(int comlexity) {
        this.comlexity = comlexity;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return comlexity == level.comlexity && Objects.equals(levelName, level.levelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comlexity, levelName);
    }
}
