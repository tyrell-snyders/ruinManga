package com.rnManga.ruinManga.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//imports
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.rnManga.ruinManga.services.iComicService;

@Service
public class ComicService implements iComicService {
    private final String baseUrl = "https://api.mangadex.org/manga";

    @Override
    public JsonNode getTrendingListNode() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String[] contentRatings = {"safe", "suggestive"};

        HttpEntity<String[]> requestEntity = new HttpEntity<>(contentRatings, headers);

        ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(
            baseUrl,
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

    @Override
    public JsonNode getMangaNode(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String mangaUrl = baseUrl + "/" + id + "/aggregate?";
        String[] translatedLanguage = {"en"};

        HttpEntity<String[]> requestEntity = new HttpEntity<>(translatedLanguage, headers);
        ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(
            mangaUrl, 
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
    public JsonNode searchMangaNode(String title) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String searchUrl = baseUrl + "?title=" + title 
                    + "&includedTagsMode=AND&excludedTagsMode=OR"
                    + "&contentRating[]=safe&contentRating[]=suggestive&contentRating[]=erotica"
                    + "&order[latestUploadedChapter]=desc";

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(
            searchUrl,
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
    public JsonNode getMangaListNode() {
        String apiUrl = baseUrl + 
            "?limit=30&includedTagsMode=AND&excludedTagsMode=OR&contentRating[]=safe&contentRating[]=suggestive&contentRating[]=erotica&order[latestUploadedChapter]=desc";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(
            apiUrl,
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

}
