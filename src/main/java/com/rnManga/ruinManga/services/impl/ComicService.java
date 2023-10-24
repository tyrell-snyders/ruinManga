package com.rnManga.ruinManga.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.rnManga.ruinManga.services.iComicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ComicService implements iComicService {
    private static final String BASE_URL = "https://api.mangadex.org/manga";
    private static final String COVER_URL = "https://api.mangadex.org/cover";
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
    public JsonNode getTrendingListNode() {
        String[] contentRatings = {"safe", "suggestive"};
        String url = BASE_URL;
        return makeRequest(url, HttpMethod.GET, contentRatings);
    }

    @Override
    public JsonNode getMangaNode(String id) {
        String url = BASE_URL + "/" + id + "/aggregate?";
        String[] translatedLanguage = {"en"};
        return makeRequest(url, HttpMethod.GET, translatedLanguage);
    }

    @Override
    public JsonNode searchMangaNode(String title) {
        String url = BASE_URL + "?title=" + title
                + "&includedTagsMode=AND&excludedTagsMode=OR"
                + "&contentRating[]=safe&contentRating[]=suggestive&contentRating[]=erotica"
                + "&order[latestUploadedChapter]=desc";
        return makeRequest(url, HttpMethod.GET, null);
    }

    @Override
    public JsonNode getMangaListNode() {
        String url = BASE_URL + "?limit=30&includedTagsMode=AND&excludedTagsMode=OR" +
                "&contentRating[]=safe&contentRating[]=suggestive&contentRating[]=erotica&order[latestUploadedChapter]=desc";
        return makeRequest(url, HttpMethod.GET, null);
    }

    @Override
    public JsonNode getCoverArtNode(String id) {
        String url = COVER_URL + "/" + id + "?includes%5B%5D=manga";
        return makeRequest(url, HttpMethod.GET, null);
    }
}