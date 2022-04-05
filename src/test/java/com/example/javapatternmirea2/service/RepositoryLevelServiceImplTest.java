package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Game;
import com.example.javapatternmirea2.entity.Level;
import com.example.javapatternmirea2.repository.LevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RepositoryLevelServiceImplTest {

    @Mock
    LevelRepository levelRepository;
    @Captor
    ArgumentCaptor<Level> captorLevel;
    @Captor
    ArgumentCaptor<Long> captorId;

    @Test
    void saveOrUpdateTest() {
        //given
        LevelService levelService = new RepositoryLevelServiceImpl(levelRepository);
        Level levelToSave = new Level(12, "name");

        //when
        levelService.saveOrUpdate(levelToSave);

        //then
        verify(levelRepository).save(captorLevel.capture());
        Level savedLevel = captorLevel.getValue();
        assertEquals(levelToSave.getLevelName(), savedLevel.getLevelName());
    }

    @Test
    void deleteTest() {
        //given
        LevelService levelService = new RepositoryLevelServiceImpl(levelRepository);
        Long id = 1L;
        Level levelToDelete = new Level(12, "name");
        levelToDelete.setId(id);

        //when
        levelService.delete(levelToDelete);

        //then
        verify(levelRepository).deleteById(captorId.capture());
        assertEquals(id, captorId.getValue());
    }
}
