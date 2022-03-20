package com.example.javapatternmirea2.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date date;
    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Level> levels;

    public Game(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Game() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name) && Objects.equals(date, game.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date);
    }
}
