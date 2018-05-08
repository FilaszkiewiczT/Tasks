package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    private URI urlBuilder() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/tomaszf3/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(urlBuilder(), TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElseGet(Collections::emptyList);
    }

}