package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Game;
import com.example.javapatternmirea2.repository.GameRepository;
import com.example.javapatternmirea2.repository.LevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RepositoryGameServiceImplTest {
    @Mock
    GameRepository gameRepository;
    @Mock
    LevelRepository levelRepository;
    @Captor
    ArgumentCaptor<Game> captorGame;
    @Captor
    ArgumentCaptor<Long> captorId;


    @Test
    void saveOrUpdateTest() {
        //given
        GameService gameService = new RepositoryGameServiceImpl(gameRepository, levelRepository);
        Game gameToSave = new Game("name", new Date());

        //when
        gameService.saveOrUpdate(gameToSave);

        //then
        verify(gameRepository).save(captorGame.capture());
        Game savedLevel = captorGame.getValue();
        assertEquals(gameToSave.getName(), savedLevel.getName());
    }

    @Test
    void deleteTest() {
        //given
        GameService gameService = new RepositoryGameServiceImpl(gameRepository, levelRepository);
        Game gameToDelete = new Game("name", new Date());
        gameToDelete.setId(1L);

        //when
        gameService.delete(gameToDelete);

        //then
        verify(gameRepository).deleteById(captorId.capture());
        Long idToDelete = captorId.getValue();
        assertEquals(gameToDelete.getId(), idToDelete);
    }

    @Test
    void getByIdTest() {
        //given
        GameService gameService = new RepositoryGameServiceImpl(gameRepository, levelRepository);
        Long id = 1L;
        Game gameToFind = new Game("name", new Date());
        gameToFind.setId(id);
        when(gameRepository.getById(id)).thenReturn(gameToFind);

        //when
        gameService.getById(id);

        //then
        assertEquals(id, gameToFind.getId());
    }
}
