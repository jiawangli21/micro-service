package com.htqyjsw1.searchpage.service;

import com.htqyjsw1.searchpage.domain.NewWordDomain;

import java.util.List;
import java.util.Map;

public interface NewWordService {
    public List<Map<String, Object>> queryNewWord(String timeType, String startTime, String endTime, int offset, int limit);

    public int countNewWord(String timeType, String startTime, String endTime);
}
