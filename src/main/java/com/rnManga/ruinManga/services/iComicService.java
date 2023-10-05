package com.rnManga.ruinManga.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface iComicService {

    JsonNode getTrendingListNode();
    JsonNode getMangaNode();
    JsonNode searchMangaNode(String title);
}
