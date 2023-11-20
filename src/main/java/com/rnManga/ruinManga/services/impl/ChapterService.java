package com.rnManga.ruinManga.services.impl;

//imports
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rnManga.ruinManga.services.iChapterService;

@Service
public class ChapterService implements iChapterService {

    private final String baseUrl = "https://api.mangadex.org/chapter/";
    private final String mangaURL = "https://api.mangadex.org/manga";
    private static final Logger logger = LoggerFactory.getLogger(ComicService.class);

    private JsonNode makeRequest(String url, HttpMethod method, Object requestObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(requestObject, headers);

        try {
            ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(url, method, requestEntity, JsonNode.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage());
        }
        return null;
    }


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

    @Override
    public JsonNode getMangaVolumesNode(String id) {
        String url = mangaURL + "/" + id + "/aggregate";
        return makeRequest(url, HttpMethod.GET, null);
    }
    
}
