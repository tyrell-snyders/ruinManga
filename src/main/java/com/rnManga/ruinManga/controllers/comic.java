package com.rnManga.ruinManga.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// Imports
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.rnManga.ruinManga.services.iComicService;

@RestController
@RequestMapping(path = "api/v1/comic")
public class comic {
    @Autowired
    private iComicService comicService;

    private final String url = "https://api.mangadex.org/manga";
    @GetMapping("/trending")
    public JsonNode getTrending() {
        JsonNode result = comicService.getTrendingListNode();
        if (result != null)
            return result;
        else
            return null;
    }

    @GetMapping(path = "/manga")
    public JsonNode getManga() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String[] contentRatings = {"safe", "suggestive"};

        HttpEntity<String[]> requestEntity = new HttpEntity<>(contentRatings, headers);

        ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(
            url, 
            HttpMethod.GET,
            requestEntity, 
            JsonNode.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    @GetMapping(path = "/search/{title}")
    public JsonNode searchManga(@PathVariable("title") String title) {
        JsonNode result = comicService.searchMangaNode(title);
        if (result != null)
            return result;
        else
            return null;
    }

}
