package com.htqyjsw1.searchpage.service;


import com.alibaba.fastjson.JSONArray;

public interface RecommendResultService {
    public JSONArray recommendResult(String userId, String recommendType);
}
