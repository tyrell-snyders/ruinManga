package com.rnManga.ruinManga.services.impl;

//imports
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rnManga.ruinManga.services.iChapterService;

@Service
public class ChapterService implements iChapterService {

    private final String baseUrl = "https://api.mangadex.org/chapter/";

    @Override
    public JsonNode getChapterNode(String id) {
        String chapterUrl = baseUrl + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(
            chapterUrl,
            HttpMethod.GET,
            requestEntity,
            JsonNode.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK)
            return responseEntity.getBody();
        else 
            return null;
    }
    
}
