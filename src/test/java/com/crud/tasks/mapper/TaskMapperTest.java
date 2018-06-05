package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"testTitle", "testContent");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(1L,(long)task.getId());
        Assert.assertEquals("testTitle",task.getTitle());
        Assert.assertEquals("testContent",task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L,"testTitle", "testContent");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(1L,(long)taskDto.getId());
        Assert.assertEquals("testTitle",taskDto.getTitle());
        Assert.assertEquals("testContent",taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task = new Task(1L,"testTitle", "testContent");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(1, taskDtos.size());
        Assert.assertEquals(1L, (long)taskDtos.get(0).getId());
        Assert.assertEquals("testTitle", taskDtos.get(0).getTitle());
        Assert.assertEquals("testContent", taskDtos.get(0).getContent());
    }
}