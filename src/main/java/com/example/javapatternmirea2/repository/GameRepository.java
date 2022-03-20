package com.example.javapatternmirea2.repository;

import com.example.javapatternmirea2.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByOrderByNameAsc();
    Optional<Game> findById(Long id);
    void deleteById(Long id);
}
