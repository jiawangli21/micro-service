package com.htqyjsw1.searchpage.service.impl;

import com.htqyjsw1.searchpage.dao.NewWordMapper;
import com.htqyjsw1.searchpage.domain.NewWordDomain;
import com.htqyjsw1.searchpage.service.NewWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewWordImpl implements NewWordService {
    @Autowired
    private NewWordMapper newWordMapper;

    /**
     * 新词发现数据查询，服务层实现方法
     * @param timeType：查询类型
     * @param startTime：起始时间
     * @param endTime：结束时间
     * @param offset：偏移量
     * @param limit：每页限制
     * @return：新词数据查询结果
     */
    @Override
    public List<Map<String, Object>> queryNewWord(String timeType, String startTime, String endTime, int offset, int limit) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (timeType.equals("day")) {
            List<NewWordDomain> newWordList = newWordMapper.queryDayNewWord();
            for (NewWordDomain newWordDomain : newWordList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("conTime", formatter.format(newWordDomain.getConTime()));
                map.put("newEntity", newWordDomain.getNewEntity().split("@#"));
                list.add(map);
            }
            return list;
        } else if (timeType.equals("week")) {
            List<NewWordDomain> newWordList = newWordMapper.queryWeekNewWord();
            for (NewWordDomain newWordDomain : newWordList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("conTime", formatter.format(newWordDomain.getConTime()));
                map.put("newEntity", newWordDomain.getNewEntity().split("@#"));
                list.add(map);
            }
            return list;
        } else if (timeType.equals("month")) {
            List<NewWordDomain> newWordList = newWordMapper.queryMonthNewWord(offset, limit);
            for (NewWordDomain newWordDomain : newWordList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("conTime", formatter.format(newWordDomain.getConTime()));
                map.put("newEntity", newWordDomain.getNewEntity().split("@#"));
                list.add(map);
            }
            return list;
        } else {
            List<NewWordDomain> newWordList = newWordMapper.queryPartNewWord(startTime, endTime, offset, limit);
            for (NewWordDomain newWordDomain : newWordList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("conTime", formatter.format(newWordDomain.getConTime()));
                map.put("newEntity", newWordDomain.getNewEntity().split("@#"));
                list.add(map);
            }
            return list;
        }
    }

    /***
     * 新词发现数据条数计算，服务层实现方法
     * @param timeType：查询类型
     * @param startTime：起始时间
     * @param endTime：结束时间
     * @return：查询条数
     */
    @Override
    public int countNewWord(String timeType, String startTime, String endTime){
        int count;
        if (timeType.equals("day")) {
            count = newWordMapper.countDayNewWord();
            return count;
        } else if (timeType.equals("week")) {
            count = newWordMapper.countWeekNewWord();
            return count;
        } else if (timeType.equals("month")) {
            count = newWordMapper.countMonthNewWord();
            return count;
        } else {
            count = newWordMapper.countPartNewWord(startTime, endTime);
            return count;
        }
    }
}
