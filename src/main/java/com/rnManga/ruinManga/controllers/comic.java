package com.rnManga.ruinManga.controllers;

//Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(path = "/manga/{id}")
    public JsonNode getManga(@PathVariable("id") String id) {
        JsonNode result = comicService.getMangaNode(id);

        if (result != null)
            return result;
        else
            return null;
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
