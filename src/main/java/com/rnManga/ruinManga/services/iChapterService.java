package com.rnManga.ruinManga.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface iChapterService {
    JsonNode getChapterNode(String id);
    JsonNode getMangaVolumesNode(String id);
    Object getPagesNode(String id);
}
