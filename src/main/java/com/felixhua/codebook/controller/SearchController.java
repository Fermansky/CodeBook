package com.felixhua.codebook.controller;

import com.felixhua.codebook.entity.ContentData;

import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private static SearchController searchController = new SearchController();

    public static List<ContentData> search(String prompt) {
        ArrayList<ContentData> resultList = new ArrayList<>();
        for(ContentData contentData : MainController.getContentDataList()) {
            if(contentData.getTitle().contains(prompt)) {
                resultList.add(contentData);
            }
        }
        return resultList;
    }

    public static SearchController getInstance() {
        return searchController;
    }
    private SearchController() {

    }
}
