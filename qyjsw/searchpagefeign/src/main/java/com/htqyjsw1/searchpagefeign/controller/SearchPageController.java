package com.htqyjsw1.searchpagefeign.controller;

import com.htqyjsw1.searchpagefeign.service.SearchPageService;
import com.htqyjsw1.searchpagefeign.vo.BigDataExhibitionVo;
import com.htqyjsw1.searchpagefeign.vo.NewWordVo;
import com.htqyjsw1.searchpagefeign.vo.RecommendResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/searchPage")
public class SearchPageController {
    @Autowired
    private SearchPageService searchPageService;

    @PostMapping(value = "/bigDataExhibition")
    public Map<String, Object> bigDataExhibition(@RequestBody BigDataExhibitionVo bigDataExhibitionVo) {
        return searchPageService.bigDataExhibition(bigDataExhibitionVo);
    }

    @PostMapping("/recommendResult")
    public Map<String, Object> recommendResult(@RequestBody RecommendResultVo recommendResultVo){
        return searchPageService.recommendResult(recommendResultVo);
    }

    @PostMapping("/newWord")
    public Map<String, Object> newWord(@RequestBody NewWordVo newWordVo){
        return searchPageService.newWord(newWordVo);
    }
}
