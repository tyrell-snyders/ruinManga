package com.rnManga.ruinManga.services;

//Imports
import com.fasterxml.jackson.databind.JsonNode;

public interface iComicService {

    JsonNode getTrendingListNode();
    JsonNode getMangaNode(String id);
    JsonNode searchMangaNode(String title);
    JsonNode getMangaListNode();
    JsonNode getCoverArtNode(String id);
    JsonNode getMangaVolumesNode(String id);
}
