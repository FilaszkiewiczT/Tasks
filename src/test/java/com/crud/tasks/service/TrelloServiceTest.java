package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService emailService;

    @Mock
    AdminConfig adminConfig;

    @Test
    public void testShouldFetchEmptyBoard() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> resultBoardDto = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertTrue(resultBoardDto.isEmpty());
    }

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> lists = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("testId", "testName", true);
        lists.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("testId", "testName", lists);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> resultBoardDto = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertFalse(resultBoardDto.isEmpty());
    }

    @Test
    public void testCreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testName", "testDescription", "testPos", "1");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto());

        //When
        CreatedTrelloCardDto trelloCard = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        Mockito.verify(emailService, times(1)).send(anyObject());
        Assert.assertTrue(trelloCard instanceof CreatedTrelloCardDto);
    }

}