package com.htqyjsw1.searchpage.service.impl;

import com.alibaba.fastjson.JSON;
import com.htqyjsw1.searchpage.service.BigDataExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BigDataExhibitionServiceImpl implements BigDataExhibitionService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 大数据分析展示服务层实现方法
     *
     * @param exhibitionType：展示类型，实时、日、周、月
     * @return：从redis查询到的结果放到map里返回
     */
    @Override
    public Map<String, Object> bigDataExhibition(String exhibitionType) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (exhibitionType.equals("realTime")) {
            map.put("fromRank", JSON.parseArray(redisTemplate.opsForList().range("from_rank", 0, 0).get(0)));
            map.put("ipRank", JSON.parseArray(redisTemplate.opsForList().range("ip_rank", 0, 0).get(0)));
            map.put("contentRank", JSON.parseArray(redisTemplate.opsForList().range("content_rank", 0, 0).get(0)));
            map.put("stateRank", JSON.parseArray(redisTemplate.opsForList().range("state_rank", 0, 0).get(0)));
        } else if (exhibitionType.equals("day")) {
            map.put("fromRank", JSON.parseArray(redisTemplate.opsForList().range("from_rank_history_day", 0, 0).get(0)));
            map.put("ipRank", JSON.parseArray(redisTemplate.opsForList().range("ip_rank_history_day", 0, 0).get(0)));
            map.put("contentRank", JSON.parseArray(redisTemplate.opsForList().range("content_rank_history_day", 0, 0).get(0)));
            map.put("stateRank", JSON.parseArray(redisTemplate.opsForList().range("state_rank_history_day", 0, 0).get(0)));
        } else if (exhibitionType.equals("week")) {
            map.put("fromRank", JSON.parseArray(redisTemplate.opsForList().range("from_rank_history_week", 0, 0).get(0)));
            map.put("ipRank", JSON.parseArray(redisTemplate.opsForList().range("ip_rank_history_week", 0, 0).get(0)));
            map.put("contentRank", JSON.parseArray(redisTemplate.opsForList().range("content_rank_history_week", 0, 0).get(0)));
            map.put("stateRank", JSON.parseArray(redisTemplate.opsForList().range("state_rank_history_week", 0, 0).get(0)));
        } else {
            map.put("fromRank", JSON.parseArray(redisTemplate.opsForList().range("from_rank_history_month", 0, 0).get(0)));
            map.put("ipRank", JSON.parseArray(redisTemplate.opsForList().range("ip_rank_history_month", 0, 0).get(0)));
            map.put("contentRank", JSON.parseArray(redisTemplate.opsForList().range("content_rank_history_month", 0, 0).get(0)));
            map.put("stateRank", JSON.parseArray(redisTemplate.opsForList().range("state_rank_history_month", 0, 0).get(0)));
        }
        return map;
    }
}
