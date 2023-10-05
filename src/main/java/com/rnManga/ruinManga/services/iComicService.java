package com.rnManga.ruinManga.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface iComicService {

    JsonNode getTrendingListNode();
    JsonNode getMangaNode(String id);
    JsonNode searchMangaNode(String title);
    JsonNode getMangaListNode();
}
