package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.mapper.TrelloMappper;
import com.crud.tasks.trello.validator.TrelloValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMappper trelloMappper;
    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMappper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filtredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMappper.mapToBoardsDto(filtredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMappper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createdTrelloCard(trelloMappper.mapToCardDto(trelloCard));
    }
}