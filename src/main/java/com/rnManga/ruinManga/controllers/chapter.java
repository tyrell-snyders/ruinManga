package com.rnManga.ruinManga.controllers;

//Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.rnManga.ruinManga.services.iChapterService;

@RestController
@RequestMapping("api/v1/manga")
public class chapter {
    @Autowired
    private iChapterService chapterService;

    @GetMapping("/chapter/{chapterID}")
    public JsonNode getChapter(@PathVariable("chapterID") String chapterID) {
        JsonNode result = chapterService.getChapterNode(chapterID);
        if (result != null)
            return result;
        else
            return null;
    }
}
