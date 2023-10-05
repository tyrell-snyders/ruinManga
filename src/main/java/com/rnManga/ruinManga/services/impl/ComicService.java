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
    public JsonNode getMangaNode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getManga'");
    }

    @Override
    public JsonNode searchMangaNode(String title) {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String searchUrl = baseUrl + "?title=" + title 
                    + "&includedTagsMode=AND&excludedTagsMode=OR"
                    + "&contentRating[]=safe&contentRating[]=suggestive&contentRating[]=erotica"
                    + "&order[latestUploadedChapter]=desc";

        HttpEntity<String> requesEntity = new HttpEntity<String>(headers);
        ResponseEntity<JsonNode> responseEntity = new RestTemplate().exchange(
            searchUrl,
            HttpMethod.GET,
            requesEntity,
            JsonNode.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK)
            return responseEntity.getBody();
        else
            return null;
    }

}
