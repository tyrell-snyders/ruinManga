package com.rnManga.ruinManga.controller;

//Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.rnManga.ruinManga.services.iChapterService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/manga")
public class ChapterController {
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

    @GetMapping("/chapter/pages/{chapterID}")
    public Object getPages(@PathVariable("chapterID") String chapterID) {
        Object result = chapterService.getPagesNode(chapterID);
        if (result != null)
            return result;
        else
            return null;
    }

    @GetMapping("/chapters/{mangaID}")
    public JsonNode getChapters(@PathVariable("mangaID") String mangaID) {
        JsonNode result = chapterService.getMangaVolumesNode(mangaID);
        if (result != null)
            return result;
        else
            return null;
    }

    @GetMapping("/chapters/feed/{mangaID}")
    public JsonNode getChaptersFeed(@PathVariable("mangaID") String mangaID, Integer volume) {
        JsonNode result = chapterService.getChaptersFeedNode(mangaID, volume);
        if (result != null)
            return result;
        else
            return null;
    }
    
}
