package com.htqyjsw1.searchpagefeign.service;

import com.htqyjsw1.searchpagefeign.vo.BigDataExhibitionVo;
import com.htqyjsw1.searchpagefeign.vo.NewWordVo;
import com.htqyjsw1.searchpagefeign.vo.RecommendResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "search-page")
@RequestMapping("/searchPage")
public interface SearchPageService {

    @PostMapping("/bigDataExhibition")
    Map<String, Object> bigDataExhibition(@RequestBody BigDataExhibitionVo bigDataExhibitionVo);

    @PostMapping("/recommendResult")
    Map<String, Object> recommendResult(@RequestBody RecommendResultVo recommendResultVo);

    @PostMapping("/newWord")
    Map<String, Object> newWord(@RequestBody NewWordVo newWordVo);
}
