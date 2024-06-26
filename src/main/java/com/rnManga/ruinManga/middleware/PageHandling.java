package com.rnManga.ruinManga.middleware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class PageHandling {

    private JsonNode chapter;
    public PageHandling(JsonNode chapter) {
        this.chapter = chapter;
    }

    public Object getChapters() {
        //Retrieve the pages from data(The JSON Node) and store them in the list
        List<String> pageList = new ArrayList<>();
        for (JsonNode data : chapter.get("data")) {
            pageList.add(data.asText());
        }

        //Create a HashMap to hold the pages list and the hash string
        Object result = new HashMap<>();
        ((Map<String, String>) result).put("hash", chapter.get("hash").asText());
        ((Map<String, List<String>>) result).put("pages", pageList);

        return result;
    }
}
