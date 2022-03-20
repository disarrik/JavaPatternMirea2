package com.example.javapatternmirea2.repository;

import com.example.javapatternmirea2.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    void deleteById(Long id);
}
