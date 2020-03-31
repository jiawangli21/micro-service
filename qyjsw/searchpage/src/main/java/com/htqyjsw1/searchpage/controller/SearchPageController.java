package com.htqyjsw1.searchpage.controller;

import com.htqyjsw1.searchpage.service.BigDataExhibitionService;
import com.htqyjsw1.searchpage.service.NewWordService;
import com.htqyjsw1.searchpage.service.RecommendResultService;
import com.htqyjsw1.searchpage.vo.BigDataExhibitionVo;
import com.htqyjsw1.searchpage.vo.NewWordVo;
import com.htqyjsw1.searchpage.vo.RecommendResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/searchPage")
public class SearchPageController {
    @Autowired
    private BigDataExhibitionService bigDataExhibitionService;

    @Autowired
    private RecommendResultService recommendResultService;

    @Autowired
    private NewWordService newWordService;


    /***
     * 大数据分析展示接口
     * @param bigDataExhibitionVo
     * @return
     */
    @PostMapping("/bigDataExhibition")
    public Map<String, Object> bigDataExhibition(@RequestBody BigDataExhibitionVo bigDataExhibitionVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", bigDataExhibitionVo.getUserName());
        map.put("userId", bigDataExhibitionVo.getUserId());
        map.put("exhibitionResult", bigDataExhibitionService.bigDataExhibition(bigDataExhibitionVo.getExhibitionType()));
        return map;
    }

    /**
     * 精准推荐接口
     *
     * @param recommendResultVo
     * @return
     */
    @PostMapping("/recommendResult")
    public Map<String, Object> recommendResult(@RequestBody RecommendResultVo recommendResultVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", recommendResultVo.getUserName());
        map.put("userId", recommendResultVo.getUserId());
        map.put("recommendResult", recommendResultService.recommendResult(recommendResultVo.getUserId(), recommendResultVo.getRecommendType()));
        return map;
    }

    /**
     * 新词发现接口
     *
     * @param newWordVo
     * @return
     */
    @PostMapping("/newWord")
    public Map<String, Object> newWord(@RequestBody NewWordVo newWordVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", newWordVo.getUserName());
        map.put("userId", newWordVo.getUserId());
        map.put("newWord", newWordService.queryNewWord(newWordVo.getTimeType(), newWordVo.getStartTime(), newWordVo.getEndTime(), Integer.parseInt(newWordVo.getOffset()), Integer.parseInt(newWordVo.getLimit())))
        ;
        map.put("count", newWordService.countNewWord(newWordVo.getTimeType(), newWordVo.getStartTime(), newWordVo.getEndTime()));
        map.put("timeName", newWordVo.getTimeName());
        map.put("timeType", newWordVo.getTimeType());
        return map;
    }

//    @GetMapping("/test")
//    public String test() {
//        String a = "success";
//        return a;
//    }
}
