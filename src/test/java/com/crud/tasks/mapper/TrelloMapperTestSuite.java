package com.crud.tasks.mapper;


import com.crud.tasks.domain.*;
import com.crud.tasks.trello.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;
    private String id = "1";
    private String name = "TestName";
    private boolean isClosed = false;
    private String description = "TestDescription";
    private String pos = "TestPos";
    private String listId = "List1";

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto(id, name, isClosed));
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto(id, name, trelloListDtos));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        Assert.assertEquals(id, trelloBoards.get(0).getId());
        Assert.assertEquals(name, trelloBoards.get(0).getName());
        Assert.assertEquals(1, trelloBoards.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList(id, name, isClosed));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard(id, name, trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals(id, trelloBoardDtos.get(0).getId());
        Assert.assertEquals(name, trelloBoardDtos.get(0).getName());
        Assert.assertEquals(1, trelloBoardDtos.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto(id, name, isClosed));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(id, trelloLists.get(0).getId());
        Assert.assertEquals(name, trelloLists.get(0).getName());
        Assert.assertEquals(isClosed, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList(id, name, isClosed));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(id, trelloListDtos.get(0).getId());
        Assert.assertEquals(name, trelloListDtos.get(0).getName());
        Assert.assertEquals(isClosed, trelloListDtos.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(name,description,pos,listId);
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals(name, trelloCardDto.getName());
        Assert.assertEquals(description, trelloCardDto.getDescription());
        Assert.assertEquals(pos, trelloCardDto.getPos());
        Assert.assertEquals(listId, trelloCardDto.getListId());
    }
    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(name,description,pos,listId);
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals(name, trelloCard.getName());
        Assert.assertEquals(description, trelloCard.getDescription());
        Assert.assertEquals(pos, trelloCard.getPos());
        Assert.assertEquals(listId, trelloCard.getListId());
    }
}


