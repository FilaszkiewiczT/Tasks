package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbServiceTest {

    @Autowired
    DbService dbService;

    @Autowired
    private TaskRepository repository;

    @Test
    public void testGetAllTasks() {

        //Given
        Task taskTest = new Task(1L, "testTitle1", "testContent1");
        Task taskTest2 = new Task(2L, "testTitle2", "testContent2");
        repository.save(taskTest);
        repository.save(taskTest2);

        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        Assert.assertEquals(2,result.size());

        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void testGetTask() {
        //Given
        Task taskTest = new Task(1L, "testTitle1", "testContent1");
        Task taskTest2 = new Task(2L, "testTitle2", "testContent2");
        Task taskTest3 = new Task(3L, "testTitle3", "testContent3");
        repository.save(taskTest);
        repository.save(taskTest2);
        repository.save(taskTest3);
        //When
        Optional<Task> result = dbService.getTask(1L);
        //Then
        assertNotEquals(null, result);
        //Clean Up
        repository.deleteAll();
    }

    @Test
    public void testSaveTask() {
        //Given
        Task taskTest = new Task(1L, "testTitle1", "testContent1");
        //When
        //Then
        Assert.assertNotNull(dbService.saveTask(taskTest));
        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task taskTest = new Task(1L, "testTitle1", "testContent1");
        repository.save(taskTest);
        //When
        dbService.deleteTask(1L);
        //Then
        Assert.assertFalse(repository.findById(taskTest.getId()).isPresent());
    }
}