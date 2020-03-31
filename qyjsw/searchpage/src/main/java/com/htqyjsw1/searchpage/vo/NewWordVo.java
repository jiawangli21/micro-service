package com.htqyjsw1.searchpage.vo;

import lombok.Data;

@Data
public class NewWordVo {
    private String userName;//用户名
    private String userId;//用户ID
    private String timeType;//时间类型
    private String timeName;//时间名称
    private String startTime;//起始时间
    private String endTime;//结束时间
    private String offset;//偏移量
    private String limit;//每页数量
}
